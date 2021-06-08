package com.example.ghtk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Spanned;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class ContactActivity extends AppCompatActivity {

    HtmlTextView htmlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        htmlTextView = findViewById(R.id.htmltv);

        Spanned spanned = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                .setHtml("<p> Liên hệ với dịch vụ của chúng tôi chưa bao giờ dễ dàng và đơn giản đến như vậy <br>" + "<br>" +
                        "        Hotline: 0912 250 721 <br>" +
                        "        Website: tuvanghtk.con.vn <br>" +
                        "        Fanpage: https://facebook.com/ghtkuit <br>" +
                        "<br>" +
                        "        Hợp tác vận chuyển quy mô lớn và lâu dài, trở thành đối tác gắn bó bền vững, vui lòng liên hệ: <br>" +
                        "        Gmail: ghtk@gmail.com <br>" +
                        "        Địa chỉ: Khu phố 6, phường Linh Trung, quận Thủ Đức, Thành phố Hồ Chí Minh.</p>")
        );
        htmlTextView.setText(spanned);

    }

}