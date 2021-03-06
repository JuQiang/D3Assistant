package iamfqq.d3assistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;

import static android.view.View.LAYER_TYPE_SOFTWARE;

public class ShowItemTooltipActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item_tooltip);

        intent = this.getIntent();
        String stringItemTooltip = intent.getStringExtra("stringItemTooltip");

        final ProgressDialog proDialog = android.app.ProgressDialog.show(this, "奈非天！", "正在和哥布林沟通，请稍候……");
        ItemTask it = new ItemTask(new TaskCompleted() {
            @Override
            public void OnTaskCompleted(Object result) {
                String html = String.valueOf(result);
                StringBuilder sb = new StringBuilder();
                sb.append("<html>").append("\r\n");
                sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"zh-cn\">").append("\r\n");
                sb.append("<head xmlns:og=\"http://ogp.me/ns#\" xmlns:fb=\"http://ogp.me/ns/fb#\">").append("\r\n");
                //sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
                //sb.append("<meta name=\"keywords\" content=\"webview Android\" />");
                sb.append("<link rel=\"stylesheet\" href=\"http://d3.blizzard.cn/css/profile/hero.css\" type=\"text/css\" media=\"all\">").append("\r\n");
                sb.append("<link rel=\"stylesheet\" href=\"http://d3.blizzard.cn/css/profile/d3.css\" type=\"text/css\" media=\"all\">").append("\r\n");
                sb.append("<link rel=\"stylesheet\" href=\"http://d3.blizzard.cn/css/profile/tooltip.css\" type=\"text/css\" media=\"all\">").append("\r\n");
                sb.append("<meta name=\"viewport\" content=\"width = 300, minimum-scale=0.5\" />");
                sb.append("</head>").append("\r\n");
                sb.append("<body style=\"background-color:black \">").append("\r\n");
                sb.append(html).append("\r\n");
                sb.append("</body>").append("\r\n");
                sb.append("</html>").append("\r\n");

                WebView browser = (WebView) findViewById(R.id.wvItemTooltip);
                //http://blog.csdn.net/top_code/article/details/9163597
                //browser.loadData(sb.toString(),"text/html","gb2312");
                D3API.WriteLog("tooltip",sb.toString());
                browser.loadDataWithBaseURL(null, sb.toString(), "text/html", "UTF-8", null);
                proDialog.dismiss();
            }
        });
        it.execute(stringItemTooltip);
//http://www.cnblogs.com/renqingping/archive/2012/10/16/MarginBorderPadding.html
        //padding, margin区别
    }
}
