package com.lbtt2801.yamevn

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lbtt2801.yamevn.navigation.NavGraphComponent
import com.lbtt2801.yamevn.utils.sign_in_google.GoogleAuthUiClient
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.SearchViewModel
import com.stevdzasan.onetap.GoogleUser
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState
import com.google.android.gms.auth.api.identity.Identity
import com.lbtt2801.yamevn.navigation.Screens
import com.lbtt2801.yamevn.screens.LoginScreen
import com.lbtt2801.yamevn.screens.ProfileScreen
import com.lbtt2801.yamevn.viewmodels.sign_in_google.SignInViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val searchViewModel: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen().apply {
//            setKeepOnScreenCondition {
//                !mainViewModel.isReady.value
//            }
//            setOnExitAnimationListener { screen ->
//                val zoomX = ObjectAnimator.ofFloat(
//                    screen.iconView,
//                    View.SCALE_X,
//                    0.5f,
//                    0.0f
//                )
//                zoomX.interpolator = OvershootInterpolator()
//                zoomX.duration = 500L
//                zoomX.doOnEnd { screen.remove() }
//
//                val zoomY = ObjectAnimator.ofFloat(
//                    screen.iconView,
//                    View.SCALE_Y,
//                    0.5f,
//                    0.0f
//                )
//                zoomY.interpolator = OvershootInterpolator()
//                zoomY.duration = 500L
//                zoomY.doOnEnd { screen.remove() }
//
//                zoomX.start()
//                zoomY.start()
//            }
//        }

        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
            ) {
                NavGraphComponent(searchViewModel = searchViewModel, viewModel = mainViewModel)
            }
        }
    }
}