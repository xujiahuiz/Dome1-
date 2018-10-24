package soexample.umeng.com.dome120181024.net;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHelper {

    private HttpRequestListener httpRequestListener;
    private static final int SUCCESS_REQUEST = 1;
    private static final int FAIL_REQUEST = 0;

    public HttpHelper(HttpRequestListener httpRequestListener) {
        this.httpRequestListener = httpRequestListener;
    }

    public HttpHelper doGet(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        doHttp(okHttpClient, request);
        return this;
    }

    public HttpHelper doPost(String url, RequestBody body) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().post(body).build();
        doHttp(okHttpClient, request);
        return this;
    }

    private void doHttp(OkHttpClient okHttpClient, final Request request) {

        final Message msg = Message.obtain();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "失败");
                msg.what = FAIL_REQUEST;
                msg.obj = e.getMessage();
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String data = response.body().string();
                    msg.what = SUCCESS_REQUEST;
                    msg.obj = data;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS_REQUEST:
                    String data = (String) msg.obj;
                    httpRequestListener.SuccessRequest(data);
                    break;
                case FAIL_REQUEST:
                    String mess = (String) msg.obj;
                    httpRequestListener.Filed(mess);
                    break;
            }
        }
    };
}
