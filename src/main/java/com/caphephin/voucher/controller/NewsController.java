package com.caphephin.voucher.controller;

import com.caphephin.voucher.model.News;
import com.caphephin.voucher.model.Product;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    Environment env;

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews(@RequestHeader(name = "accept-language", required=false) String language) throws IOException {


        if(language !=null && language.toLowerCase().equals("vi")) {

            List<News> newsList = new ArrayList<>();

            String imageUrl = env.getProperty("imageUrl");

            newsList.add(new News("Phin được đài truyền hình Teny Niigata phát sóng", "Chuyên mục khoe khoan một tí, sau khi lần trước được Đài truyền hình *Niigta Nippo* ghi hình vào ngày khai trương (13/1/2021) thì lần này “Phin” lại tiếp tục được Đài truyền hình *Teny Niigata* quan tâm và ghi hình. Chương trình với nội dung là ” Đi dạo cùng Hori” tuy đã được phát sóng vào thứ sáu (28/5/2021) vừa rồi. ￼Hôm nay lại nhận được thư cảm ơn và đĩa DVD lưu niệm từ Đài truyền hình Teny Niigata. Thật sự rất biết ơn và cảm kích trước sự quan tâm của các đơn vị truyền thông trong thời gian gần 5 tháng kể từ ngày “Phin” ra mắt vào ngày 13/1/2021. Phin và đội ngũ của mình xin hứa sẽ luôn cố gắng để có thể mang được một phần gì đó tinh hoa trong ẩm thực cũng như những nét văn hoá của Việt Nam mình đến với bạn bè Quốc Tế .", imageUrl + "news/news2.png", "<div class='column is-three-quarters has-text-justified img-100'><h1 class='is-size-4 has-text-weight-bold color-01 p-t-30 p-b-20'>Chuyên mục khoe khoan một tí, sau khi lần trước được Đài truyền hình *Niigta Nippo* ghi hình vào ngày khai trương (13/1/2021) thì lần này “Phin” lại tiếp tục được Đài truyền hình *Teny Niigata* quan tâm và ghi hình. Chương trình với nội dung là ” Đi dạo cùng Hori” tuy đã được phát sóng vào thứ sáu (28/5/2021) vừa rồi. ￼Hôm nay lại nhận được thư cảm ơn và đĩa DVD lưu niệm từ Đài truyền hình Teny Niigata. Thật sự rất biết ơn và cảm kích trước sự quan tâm của các đơn vị truyền thông trong thời gian gần 5 tháng kể từ ngày “Phin” ra mắt vào ngày 13/1/2021. Phin và đội ngũ của mình xin hứa sẽ luôn cố gắng để có thể mang được một phần gì đó tinh hoa trong ẩm thực cũng như những nét văn hoá của Việt Nam mình đến với bạn bè Quốc Tế .</p><div style='width: 720px;' class='wp-video'><!--[if lt IE 9]><script>document.createElement('video');</script><![endif]--><video class='wp-video-shortcode' id='video-14089-1' width='720' height='480' preload='metadata' controls='controls'><source type='video/mp4' src='https://caphephin.jp/wp-content/uploads/2021/09/Teny-Niigata-28.5.2021.VOB_.mp4?_=1'><a href='https://caphephin.jp/wp-content/uploads/2021/09/Teny-Niigata-28.5.2021.VOB_.mp4'>https://caphephin.jp/wp-content/uploads/2021/09/Teny-Niigata-28.5.2021.VOB_.mp4</a></video></div></div>"));
            newsList.add(new News("Phin được lên truyền hình Niigata Nippo", "Xin chào mọi người, lại là *Phin* đây.Xoay qua xoay lại phát mà cũng hơn nửa năm kể từ ngày *Phin* chính thức ra mắt trình làng với mọi người rồi, hôm nay tuy muộn nhưng cho phép *Phin* xin chia sẽ chuyện cũ một tí đó là vào ngày 13/1/2021 *Phin* chính thức ra mắt cửa hàng đầu tiên trên nước Nhật tại thành phố Niigata-Tỉnh Niigata với phuơng châm *Mang hương vị cafe Việt bay cao bay xa đến với bạn bè quốc tế* thì may mắn thay ngoài việc được khách hàng,bạn bè thân thuộc đến chia vui ủng hộ *Phin* thì *Phin* còn nhận được sự quan tâm và hỗ trợ từ đài truyền hình *Niigata Nippo* đã đến quay hình và truyền hình *Phin* lên tivi nhân ngày *Phin* khai trương", imageUrl + "news/news1.png", "<div class='column is-three-quarters has-text-justified img-100'><h1 class='is-size-4 has-text-weight-bold color-01 p-t-30 p-b-20'>Xin chào mọi người, lại là *Phin* đây.Xoay qua xoay lại phát mà cũng hơn nửa năm kể từ ngày *Phin* chính thức ra mắt trình làng với mọi người rồi, hôm nay tuy muộn nhưng cho phép *Phin* xin chia sẽ chuyện cũ một tí đó là vào ngày 13/1/2021 *Phin* chính thức ra mắt cửa hàng đầu tiên trên nước Nhật tại thành phố Niigata-Tỉnh Niigata với phuơng châm *Mang hương vị cafe Việt bay cao bay xa đến với bạn bè quốc tế* thì may mắn thay ngoài việc được khách hàng,bạn bè thân thuộc đến chia vui ủng hộ *Phin* thì *Phin* còn nhận được sự quan tâm và hỗ trợ từ đài truyền hình *Niigata Nippo* đã đến quay hình và truyền hình *Phin* lên tivi nhân ngày *Phin* khai trương. Đây quả thật là một món quà tinh thần *siêu tỏ khổng lồ* đối với tập thể *CaPhePhin* bọn mình, tiếp thêm động lực cho bọn mình có thể ngày một củng cố niềm tin và quyết tâm để đưa đến tay bạn bè *Năm Châu Bốn Bể* những ly cafe Việt Nam siêu ngon nhằm thông qua đó có thể một phần nào đó quảng bá hình ảnh của cafe Việt-ẩm thực Việt đến rộng rãi hơn với bạn bè quốc tế.Ở *Phin* không đơn thuẩn chỉ là cafe Việt-Bánh mỳ Việt mà phía sau đó còn có những câu chuyện- nét văn hoá đặc trưng- một môi trường kết nối người Việt Nam nơi đất khách quê người với nhau vì thế *Phin* mong sẽ luôn luôn nhận được sự ủng hộ-yêu thương-kết nối từ phía mọi người.Cuối cùng *Phin* một lần nữa xin gửi lời cảm ơn chân thành và sâu sắc đến với các khách hàng-bạn bè-các cơ quan truyền thông  địa phương đã luôn bên cạnh-ủng hộ- đồng hành cùng *Phin* nhé.</span></p><div style='width: 720px;' class='wp-video'><!--[if lt IE 9]><script>document.createElement('video');</script><![endif]--><video class='wp-video-shortcode' id='video-14083-1' width='720' height='480' preload='metadata' controls='controls'><source type='video/mp4' src='https://caphephin.jp/wp-content/uploads/2021/09/Niigata-Nippo.mp4?_=1'><a href='https://caphephin.jp/wp-content/uploads/2021/09/Niigata-Nippo.mp4'>https://caphephin.jp/wp-content/uploads/2021/09/Niigata-Nippo.mp4</a></video></div></div>"));


            return new ResponseEntity<>(newsList, HttpStatus.OK);
        }

        List<News> newsList = new ArrayList<>();

        String imageUrl = env.getProperty("imageUrl");

        newsList.add(new News("実際に先週の金曜日(5月28日)にTeNy新潟テレビ「ほり散歩」という番組に僕たちの”Phin” を紹介していただきました。", "今日にちょっと自慢のことを皆さんに紹介したいと思います。 実際に先週の金曜日(5月28日)にTeNy新潟テレビ「ほり散歩」という番組に僕たちの”Phin” を紹介していただきました。今日、また感謝手紙と記念DVDを送っていただきました。ありがとうございます。 グランドオープンから今まで地方におけるメディアの皆さんが色々サポートしてくれて外国人の私たちにとって本当にありがたいです。 また、何か一緒にできれば是非お願い申し上げます。", imageUrl + "news/news2.png", "<div class='column is-three-quarters has-text-justified img-100'><h1 class='is-size-4 has-text-weight-bold color-01 p-t-30 p-b-20'>実際に先週の金曜日(5月28日)にTeNy新潟テレビ「ほり散歩」という番組に僕たちの”Phin” を紹介していただきました。</h1><p>今日にちょっと自慢のことを皆さんに紹介したいと思います。<br>実際に先週の金曜日(5月28日)にTeNy新潟テレビ「ほり散歩」という番組に僕たちの”Phin” を紹介していただきました。今日、また感謝手紙と記念DVDを送っていただきました。ありがとうございます。<br>グランドオープンから今まで地方におけるメディアの皆さんが色々サポートしてくれて外国人の私たちにとって本当にありがたいです。<br>また、何か一緒にできれば是非お願い申し上げます。</p><div style='width: 720px;' class='wp-video'><!--[if lt IE 9]><script>document.createElement('video');</script><![endif]--><video class='wp-video-shortcode' id='video-14089-1' width='720' height='480' preload='metadata' controls='controls'><source type='video/mp4' src='https://caphephin.jp/wp-content/uploads/2021/09/Teny-Niigata-28.5.2021.VOB_.mp4?_=1'><a href='https://caphephin.jp/wp-content/uploads/2021/09/Teny-Niigata-28.5.2021.VOB_.mp4'>https://caphephin.jp/wp-content/uploads/2021/09/Teny-Niigata-28.5.2021.VOB_.mp4</a></video></div></div>"));
        newsList.add(new News("新潟における新潟日報会社でも*Phin*を関心して、テレビで紹介していただきました", "皆さん、こんにちは！ *Phin*です。 グランドオープンからもう半年以上を経っています。グランドオープンの日に皆さんから色々お祝いとかご応援などをいただいて本当にありがたいです。特に新潟における新潟日報会社でも*Phin*を関心して、テレビで紹介していただきました。*Phin*にとって本当に自慢な事です。 これからも宜しくお願いします。  ", imageUrl + "news/news1.png", "<div class='column is-three-quarters has-text-justified img-100'><h1 class='is-size-4 has-text-weight-bold color-01 p-t-30 p-b-20'>新潟における新潟日報会社でも*Phin*を関心して、テレビで紹介していただきました</h1><p><span style='font-weight: 400;'>皆さん、こんにちは！</span><span style='font-weight: 400;'><br></span><span style='font-weight: 400;'>*Phin*です。</span><span style='font-weight: 400;'><br></span><span style='font-weight: 400;'>グランドオープンからもう半年以上を経っています。グランドオープンの日に皆さんから色々お祝いとかご応援などをいただいて本当にありがたいです。特に新潟における新潟日報会社でも*Phin*を関心して、テレビで紹介していただきました。*Phin*にとって本当に自慢な事です。</span><span style='font-weight: 400;'></span><span style='font-weight: 400;'>これからも宜しくお願いします。</span></p><div style='width: 720px;' class='wp-video'><!--[if lt IE 9]><script>document.createElement('video');</script><![endif]--><video class='wp-video-shortcode' id='video-14083-1' width='720' height='480' preload='metadata' controls='controls'><source type='video/mp4' src='https://caphephin.jp/wp-content/uploads/2021/09/Niigata-Nippo.mp4?_=1'><a href='https://caphephin.jp/wp-content/uploads/2021/09/Niigata-Nippo.mp4'>https://caphephin.jp/wp-content/uploads/2021/09/Niigata-Nippo.mp4</a></video></div></div>"));

        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }
}