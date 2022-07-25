import Navbar from "../../components/Navbar";
import '../../styles/ISOtext.css'
import constructMessage from "../../assets/image/cautruciso8583.PNG";
import bitmap from "../../assets/image/bitmaps.PNG";
const ISOtext = () => {
    return (
        <div className="content">
            <h1 id="title"><strong>Message ISO</strong></h1>
            <p className="content-1">là tiêu chuẩn quốc tế về sự liên lạc, tương tác của các hệ thống ngân hàng với nhau (các giao dịch,
                message tài chính).
                ISO 8583 đưa ra định dạng message và luồng giao tiếp để các hệ thống khác nhau có thể trao đổi các
                giao dịch.
            </p>
            <h1 id="title"><strong>Cấu trúc thông điệp</strong></h1>
            <p className="content-1">Các giao dịch qua hệ thống Switch tuân theo chuẩn thông điệp ISO 8583:1987.</p>
            <h3 id="title"><strong>Cấu trúc thông điệp</strong></h3>
            <p className="content-1">Mỗi thông điệp gồm các trường thông tin được sắp xếp theo thứ tự sau: thông tin
                header, kiểu nhận dạng thông điệp (Message Type Identifier: MTI), 1 hoặc 2 hoặc 3
                Bitmaps và một dãy các trường trong bảng các thành phần dữ liệu (data elements) đã
                được xác định trong Bitmaps. Hình dưới đây thể hiện thứ tự của các trường thông tin
                này.</p>
            <img className="img" src={constructMessage} alt="Construct Message"/>
            <h3 id="title"><strong>Thông tin header</strong></h3>
            <p className="content-1">Gồm 4 byte ký tự ASCII dùng để chỉ rõ độ dài của thông điệp, độ dài này không bao gồm phần header.
                <br/>
                <br/>
                Ví dụ: Nếu một thông điệp có độ dài 128 byte, thì giá trị header “0128” sẽ được thêm
                vào phần đầu của thông điệp. Vì vậy độ dài thực sự của dữ liệu được gửi đi là 132 byte
            </p>
            <h3 id="title"><strong>Kiểu nhận dạng thông điệp (MTI)</strong></h3>
            <p className="content-1">Trường đầu tiên của mỗi thông điệp bao gồm 4 ký tự số dùng để xác định các thông tin
                gồm: phiên bản của thông điệp (message version number), lớp thông điệp (message
                class), chức năng của thông điệp (message function) và bên khởi tạo giao dịch
                (transaction originator).
            </p>
            <h3 id="title"><strong>Bitmaps</strong></h3>
            <p className="content-1">Thành phần thứ hai của thông điệp là Bitmaps. Bitmaps là một chuỗi dài 64 ký tự gồm
                các số [0,1]. Theo thứ tự của chuỗi, số 1 thể hiện sự tồn tại của trường dữ liệu tương ứng
                và số 0 thể hiện không tồn tại trường dữ liệu (thành phần dữ liệu) tương ứng ở vị
                trí đó. Một thông điệp luôn tồn tại Bitmaps thứ nhất (có thể mở rộng thêm các Bitmaps
                thứ 2 hoặc thứ 3). Để giảm bớt kích cỡ thông điệp khi truyền, người ta thường đổi chuỗi
                64 ký tự [0,1] (số nhị phân) đó sang dạng số Hexa thành một chuỗi gồm 16 ký tự số và
                chữ. Tại điểm xử lý các thông điệp sẽ chuyển đổi dãy 16 ký tự đó thành dãy 64 ký tự
                [0,1] để đọc các thành phần dữ liệu tiếp theo của thông điệp. Sau khi chuyển đổi dãy số
                Hexa sang dãy số nhị phân thì số nhị phân đầu tiên là số 0 thì có nghĩa không có Bitmaps
                thứ 2, nếu là số 1 có nghĩa là có Bitmaps thứ 2.
            </p>
            <img className="img" src={bitmap} alt="Construct Message"/>
            <h3 id="title"><strong>Các thành phần dữ liệu (data elements)</strong></h3>
            <p className="content-1">
                Thành phần thứ ba của thông điệp và nội dung của thành phần dữ liệu này tạo nên
                chuỗi các thành phần dữ liệu.
                <br/>
                <br/>
                Các thông điệp được tạo nên bởi việc sử dụng ánh xạ của Bitmaps (ánh xạ bit) đánh
                chỉ mục cho các thành phần dữ liệu được thể hiện (như trong phần Bitmaps được trình
                bày ở trên, nếu bit ứng với thành phần dữ liệu đó bằng 1 thì thành phần dữ liệu đó sẽ
                có mặt trong danh mục các thành phần dữ liệu chứa trong thông điệp đó).
                <br/>
                <br/>
                Hầu hết thành phần dữ liệu có độ dài cố định. Độ dài thực tế của các thành phần dữ
                liệu có độ dài thay đổi được cung cấp trong phần đầu cố định của thành phần dữ liệu
                đó.
            </p>
            <h4 id="title"><strong>Mô tả các thành phần dữ liệu</strong></h4>
            <h5 id="title"><strong>Primary Bitmap</strong></h5>
            <p className="content-1">
                Định dạng: b-16
                <br/>
                <br/>
                Độ dài: 16 bytes
                <br/>
                <br/>
                Trường Bitmaps thứ nhất được đòi hỏi trong tất cả các thông điệp nhằm xác định thành
                phần dữ liệu nào sẽ có mặt. Các bit sẽ được biên dịch từ trái sang phải, bit nào có giá
                trị bằng 1 sẽ chỉ ra thành phần dữ liệu ứng với vị trí bit đó sẽ có mặt, bit nào có giá trị
                bằng 0 thì thành phần dữ liệu tương ứng với bit đó không có mặt.
                <br/>
                Vị trí bit đầu tiên của trường này sẽ chỉ ra có hay không thành phần Bitmap thứ 2. Nếu
                bằng 0 là không có, nếu bằng 1 tương ứng với có mặt Bitmap thứ hai.

            </p>
            <h5 id="title"><strong>Secondary Bitmap</strong></h5>
            <p className="content-1">
                Định dạng: b-16
                <br/>
                <br/>
                Độ dài: 16 bytes
                <br/>
                <br/>
                Trường này biểu diễn các thành phần dữ liệu từ vị trí 65 đến vị trí 128. Mô tả về trường
                này tương tự như thành phần Bitmap thứ nhất.
            </p>
            <h5 id="title"><strong>Primary Account Number (PAN)</strong></h5>
            <p className="content-1">
                Với các dịch vụ chuyển mạch thẻ qua ATM, POS:
                <br/>
                <br/>
                Định dạng: an…19, LLVAR
                <br/>
                <br/>
                Độ dài: 2 bytes độ dài cộng với dữ liệu.
                <br/>
                <br/>
                Trường này biểu diễn số PAN của chủ thẻ.Trường này được sử dụng đối với tất cả các
                số tài khoản/số thẻ với việc lên tới 19 số độ dài. Các hệ thống chuyển mạch sẽ đòi hỏi
                trường này phải có mặt trong các thông điệp giao dịch 02xx, 04xx được gửi tới. Số PAN
                cũng được sử dụng để định tuyến trên Switch.
                <br/>
                <br/>
                Ví dụ: để biểu diễn số PAN có giá trị “2727279000147221” giá trị của trường này sẽ
                bằng: “162727279000147221”. Số “16” để chỉ có 16 ký tự tiếp sau.
                <br/>
                <br/>
                Đối với dịch vụ Payment code trong 2 thông điệp:
                <ul>
                    <li>Thông điệp xác thực Payment code</li>
                    <li>Thông điệp rút tiền Payment code</li>
                </ul>
                Trường này sẽ chứa thông tin mã Payment code. Payment code có độ dài là 9 ký tự,
                bao gồm các số từ 0-9.
            </p>
        </div>
    )
}
export default ISOtext