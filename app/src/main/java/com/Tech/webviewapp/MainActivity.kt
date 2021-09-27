package com.Tech.webviewapp

import android.content.DialogInterface
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      initialize()

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Toast.makeText(this@MainActivity, url, Toast.LENGTH_SHORT).show()
                ProgressBar.visibility=View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                ProgressBar.visibility=View.GONE
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true



    }
  private fun initialize(){
      webView.loadUrl("https://www.youtube.com/")
   }
    fun callUrl(view: View) {

        var url = et_url?.text.toString()
        webView.loadUrl("https://www.youtube.com/")
        when (view.id) {

            R.id.btn_search -> {
                webView.loadUrl("https://${url}")
            }
            R.id.btn_songs -> {
                webView.loadUrl("https://www.youtube.com/results?search_query=songs")
            }
            R.id.btn_movie -> {
                webView.loadUrl("https://www.youtube.com/results?search_query=movies")
            }
            R.id.btn_news -> {
                webView.loadUrl("https://www.youtube.com/results?search_query=news")
            }

        }
    }
//back button
    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Exit App")
            alertDialog.setMessage("Do you want to exit?")
            alertDialog.setIcon(R.drawable.ic_baseline_exit_to_app_24)
            alertDialog.setCancelable(false)


            var listener = DialogInterface.OnClickListener { dialogInterface, which ->
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    super.onBackPressed()
                    finish()
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    dialogInterface.dismiss()

                } else if (which == DialogInterface.BUTTON_NEUTRAL) {
                    dialogInterface.dismiss()
                }

            }
            alertDialog.setPositiveButton("yes",listener)
            alertDialog.setNegativeButton("no",listener)
            alertDialog.setNeutralButton("cancel",listener)
            alertDialog.show()
        }
        }
    }

