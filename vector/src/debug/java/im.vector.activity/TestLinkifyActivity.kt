package im.vector.activity

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import im.vector.R
import im.vector.listeners.IMessagesAdapterActionsListener
import im.vector.util.MatrixLinkMovementMethod
import im.vector.util.vectorCustomLinkify
import im.vector.util.openUrlInExternalBrowser
import org.matrix.androidsdk.crypto.data.MXDeviceInfo
import org.matrix.androidsdk.rest.model.Event


class TestLinkifyActivity : AppCompatActivity() {

    @BindView(R.id.test_linkify_scrollview)
    lateinit var scrollView: ScrollView
    @BindView(R.id.test_linkify_content_view)
    lateinit var scrollContent: LinearLayout
    @BindView(R.id.test_linkify_coordinator)
    lateinit var coordinatorLayout: CoordinatorLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_linkify)
        ButterKnife.bind(this)

        scrollContent.removeAllViews()

        listOf<String>(
                "https://www.html5rocks.com/en/tutorials/webrtc/basics/ |",
                "https://www.html5rocks.com/en/tutorials/webrtc/basics/",
                "mailto mailto:test@toto.com  test@toto.com",
                "Here is the link.www.test.com/foo/?23=35 you got it?",
                "www.lemonde.fr",
                " /www.lemonde.fr",
                "://www.lemonde.fr",
                "file:///dev/null ",
                " ansible/xoxys.matrix#2c0b65eb",
                "foo.ansible/xoxys.matrix#2c0b65eb",
                "foo.ansible.fpo/xoxys.matrix#2c0b65eb",
                "https://foo.ansible.fpo/xoxys.matrix#2c0b65eb",
                "@vf:matrix.org",
                "+44 207 123 1234",
                "+33141437940",
                "1234",
                "3456.34,089",
                "ksks9808",
                "For example: geo:48.85828,2.29449?z=16 should be clickable",
                "geo:37.786971,-122.399677;u=35",
                "37.786971,-122.399677;u=35",
                "48.107864,-1.712153",
                "synchrone peut tenir la route la",
                "that.is.some.sexy.link",
                "test overlap 48.107864,0673728392 geo + pn?",
                "test overlap 0673728392,48.107864 geo + pn?"
        ).forEach {
            val layout = LayoutInflater.from(this).inflate(R.layout.item_test_linkify, scrollContent, false)
            layout.findViewById<TextView>(R.id.test_linkify_auto_text)?.let { tv ->
                tv.text = it
                tv.movementMethod = MatrixLinkMovementMethod(object : MockMessageAdapterActionListener() {
                    override fun onURLClick(uri: Uri?) {
                        Snackbar.make(coordinatorLayout, "URI Clicked: $uri", Snackbar.LENGTH_LONG)
                                .show()

                    }
                })
            }
            layout.findViewById<TextView>(R.id.test_linkify_custom_text)?.let { tv ->
                tv.text = it
                tv.movementMethod = MatrixLinkMovementMethod(object : MockMessageAdapterActionListener() {
                    override fun onURLClick(uri: Uri?) {
                        Snackbar.make(coordinatorLayout, "URI Clicked: $uri", Snackbar.LENGTH_LONG)
                                .setAction("open", {
                                    openUrlInExternalBrowser(tv.context, uri)
                                })
                                .show()

                    }
                })
                vectorCustomLinkify(tv)
            }

            scrollContent.addView(layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))

        }
    }

}

open class MockMessageAdapterActionListener : IMessagesAdapterActionsListener {
    override fun onRowClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRowLongClick(position: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onContentClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onContentLongClick(position: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAvatarClick(userId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAvatarLongClick(userId: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSenderNameClick(userId: String?, displayName: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMediaDownloaded(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMoreReadReceiptClick(eventId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGroupFlairClick(userId: String?, groupIds: MutableList<String>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onURLClick(uri: Uri?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shouldHighlightEvent(event: Event?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMatrixUserIdClick(userId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoomAliasClick(roomAlias: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoomIdClick(roomId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEventIdClick(eventId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGroupIdClick(groupId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInvalidIndexes() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEventAction(event: Event?, textMsg: String?, action: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onE2eIconClick(event: Event?, deviceInfo: MXDeviceInfo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEventDecrypted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSelectedEventChange(currentSelectedEvent: Event?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

