package com.aleangelozi.mysamplechatappkotlin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import zendesk.chat.Chat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Chat.INSTANCE.init(
            this, BuildConfig.ZENDESK_CHAT_ACCOUNT_KEY,
            BuildConfig.ZENDEX_APP_ID
        )

        val mChatButton = findViewById<Button>(R.id.chat_button)

        val chat = ChatZenDesk()

        mChatButton.setOnClickListener {

            chat.initializeChatSdk(this)

        }

    }

}