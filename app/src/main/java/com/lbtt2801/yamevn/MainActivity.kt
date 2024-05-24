package com.lbtt2801.yamevn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.lbtt2801.yamevn.navigation.NavGraphComponent
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.SearchViewModel

class MainActivity : ComponentActivity() {
    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private val mainViewModel by viewModels<MainViewModel>()
    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()

        val signInLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account = task.result
                    account?.idToken?.let { token ->
                        mainViewModel.signIn(token)
                    }
                }
            }

        setContent {
            val navController = rememberNavController()
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
            ) {
                NavGraphComponent(
                    navController = navController,
                    searchViewModel = searchViewModel,
                    viewModel = mainViewModel,
                    onGoogleSignIn = {
                        signInLauncher.launch(googleSignInClient.signInIntent)
                        navController.popBackStack()
                    },
                    onLogout = {
                        mainViewModel.signOut()
                        signOut()
                    }
                )
            }
        }

    }

    private fun signOut() {
        firebaseAuth.signOut()
        googleSignInClient.signOut().addOnCompleteListener(this) {
            if (it.isSuccessful) {
                googleSignInClient.revokeAccess().addOnCompleteListener(this) {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.web_client_id))
                        .requestEmail()
                        .build()
                    googleSignInClient = GoogleSignIn.getClient(this, gso)
                }
            }
        }
    }
}