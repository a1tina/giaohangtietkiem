package com.example.ghtk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class RegulationActivity extends AppCompatActivity {
    HtmlTextView htmlTextView1, htmlTextView2, htmlTextView3, htmlTextView4;
    Button button1, button2, button3, button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulation);

        htmlTextView1 = findViewById(R.id.htmltv_1);
        htmlTextView4 = findViewById(R.id.htmltv_4);
        htmlTextView3 = findViewById(R.id.htmltv_3);
        htmlTextView2 = findViewById(R.id.htmltv_2);
        button1 = findViewById(R.id.b_question1);
        button2 = findViewById(R.id.b_question2);
        button3 = findViewById(R.id.b_question3);
        button4 = findViewById(R.id.b_question4);

        Spanned spanned1 = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                .setHtml("<p>Theo quy định của Nhà nước, các loại hàng hoá sau bị cấm gửi:<br>" +
                        "        1. Các chất ma tuý và chất kích thích thần kinh.<br>" +
                        "        2. Vũ khí đạn dược, trang thiết bị kỹ thuật quân sự, hiện vật thuộc di tích văn hoá lịch sử.<br>" +
                        "        3. Các loại văn hoá phẩm đồi truỵ, phản động, ấn phẩm tài liệu nhằm phá hoại trật tự công cộng chống lại Nhà nước Cộng hoà xã hội Chủ nghĩa Việt Nam.<br>" +
                        "        4. Vật hoặc chất dễ nổ, dễ cháy và các chất gây nguy hiểm hoặc làm mất vệ sinh, gây ô nhiễm môi trường.<br>" +
                        "        5. Các loại vật phẩm hàng hoá mà Nhà nước cấm lưu thông, cấm kinh doanh, cấm xuất nhập khẩu.<br>" +
                        "        6. Vật phẩm, ấn phẩm, hàng hoá cấm nhập vào nước nhận theo thông báo của Liên minh Bưu chính thế giới (UPU).<br>" +
                        "        7. Thư trong bưu kiện (thư gửi kèm trong hàng hoá).<br>" +
                        "        8. Vật phẩm, hàng hoá trong thư, ấn phẩm, học phẩm dùng cho người mù.<br>" +
                        "        9. Bưu gửi chứa nhiều bưu gửi, gửi cho nhiều địa chỉ nhận khác nhau.<br>" +
                        "        10. Sinh vật sống.<br>" +
                        "        11. Tiền Việt Nam, tiền nước ngoài và các loại giấy tờ khác có giá trị như tiền.<br>" +
                        "        12. Các loại kim khí quý (vàng, bạc, bạch kim, v.v).<br>" +
                        "        Lưu ý: GHTK không hỗ trợ giao những hàng hoá nằm trong danh mục hàng hoá cấm vận chuyển, nếu quý khách cố ý gửi các hàng hoá trên, GHTK sẽ không chịu trách nhiệm nếu xảy ra hư hỏng, thất lạc hoặc bị pháp luật truy cứu.</p>")
        );
        htmlTextView1.setText(spanned1);
        htmlTextView1.setVisibility(HtmlTextView.GONE);

        Spanned spanned2 = HtmlFormatter.formatHtml(new HtmlFormatterBuilder()
                .setHtml("<p>Hàng gửi có điều kiện được quy định như sau:<br>" +
                        "        1. Bưu phẩm, bưu kiện chứa hàng hoá để kinh doanh phải có chứng từ thuế và các chứng từ hợp lệ khác theo quy định của pháp luật.<br>" +
                        "        2. Vật phẩm, hàng hoá xuất khẩu, nhập khẩu thuộc quản lý chuyên ngành phải thực hiện theo quy định của cơ quan quản lý chuyên ngành có thẩm quyền.<br>" +
                        "        3. Ong, tằm, đỉa, côn trùng phục vụ nghiêm cứu khoa học, vật phẩm, hàng hoá dễ hư hỏng, chất bột đóng gói phải đảm bảo để không hư hỏng, ô nhiễm bưu phẩm, bưu kiện khác.<br>" +
                        "        4. Vật phẩm, hàng hoá gửi trong bưu phẩm, bưu kiện sử dụng dịch vụ máy bay phải tuân thủ những quy định về an ninh hàng không.<br>" +
                        "        Lưu ý: GHTK sẽ nhận hàng hoá gửi có điều kiện nếu khách hàng tuân theo và thực hiện đủ đúng các điều kiện ràng buộc đối với loại hàng hoá đó. Nếu quý khách cố tình gửi hàng không tuân theo quy định, GHTK sẽ không chịu trách nhiệm nếu xảy ra hư hỏng, thất lạc hoặc bị pháp luật truy cứu.</p>")
        );
        htmlTextView2.setText(spanned2);
        htmlTextView2.setVisibility(HtmlTextView.GONE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(htmlTextView1.getTag() != "noclick") {
                    htmlTextView1.setVisibility(HtmlTextView.VISIBLE);
                    htmlTextView1.setTag("noclick");
                }
                else {
                        htmlTextView1.setVisibility(HtmlTextView.GONE);
                        htmlTextView1.setTag("clicked");
                }
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(htmlTextView2.getTag() != "noclick") {
                    htmlTextView2.setVisibility(HtmlTextView.VISIBLE);
                    htmlTextView2.setTag("noclick");
                }
                else {
                    htmlTextView2.setVisibility(HtmlTextView.GONE);
                    htmlTextView2.setTag("clicked");
                }
            }
        });

    }
}