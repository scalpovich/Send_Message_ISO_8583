import '../../styles/ISOtext.css'
import constructMessage from "../../assets/image/cautruciso8583.PNG";
import bitmap from "../../assets/image/bitmaps.PNG";

const ISOtext = () => {
    return (
        <div className="content">
            <h1 id="title"><strong>ISO 8583</strong></h1>
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
            <h5 id="title"><strong>#2 Primary Account Number (PAN)</strong></h5>
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
            <h5 id="title"><strong>#3 Processing Code</strong></h5>
            <p className="content-1">
                Định dạng: n-6
                <br/>
                <br/>
                Độ dài: 6 bytes
                <br/>
                <br/>
                Trường này được đòi hỏi trong tất cả các thông điệp 02xx và 04xx.Các hệ thống sử
                dụng trường mã xử lý để xác định loại hình giao dịch được yêu cầu. Trường mã xử lý
                bao gồm 3 thành phần con sau:
                <ul>
                    <li>Hai số đầu là mã xử lý loại giao dịch</li>
                    <li>Hai số tiếp theo là “from account”</li>
                    <li>Hai số cuối là “to account”</li>
                </ul>
            </p>
            <h5 id="title"><strong>#4 Transaction Amount</strong></h5>
            <p className="content-1">
                Định dạng: n-12
                <br/>
                <br/>
                Độ dài: 12 bytes
                <br/>
                <br/>
                Trường này biểu diễn số tiền giao dịch theo đơn vị tiền tệ của bên chấp nhận thẻ được
                sử dụng bởi chủ thẻ tại điểm dịch vụ. Trường số tiền giao dịch luôn luôn biểu diễn số
                tiền giao dịch gốc (số tiền giao dịch khởi điểm đầu tiên của giao dịch).
                <br/>
                <br/>
                Đơn vị tiền tệ phải được chỉ ra trong trường DE #49 (Currency Code) và hệ thống sẽ
                tham chiếu đến trường này như đơn vị tiền tệ của nơi chấp nhận thẻ hoặc tiền tệ của
                giao dịch tại điểm dịch vụ.
                <br/>
                <br/>
                Giá trị trường này sẽ được điền “0” và được căn phải, khoảng dành cho số thập phân
                được áp dụng trong trường số tiền giao dịch này (NAPAS quy định có hai chữ số thập
                phân).
                <br/>
                <br/>
                Ví dụ khi ta muốn biểu diễn số tiền giao dịch là hai trăm nghìn “200.000 VNĐ”, giá trị
                của trường này bằng 000020000000 (chú ý có khoảng gồm 2 chữ số dành cho số thập
                phân). Còn giá trị của trường mã tiền tệ sẽ bằng 704 để chỉ đó là VNĐ.
            </p>
            <h5 id="title"><strong>#5 Settlement Amount</strong></h5>
            <p className="content-1">
                Định dạng: n-12
                <br/>
                <br/>
                Độ dài: 12 bytes
                <br/>
                <br/>
                Số tiền quyết toán bằng số tiền giao dịch thực tế (DE #4) * Tỷ giá chuyển đổi tiền tệ từ
                đồng tiền giao dịch sang đồng tiền quyết toán (DE #9), không bao gồm phí dịch vụ trong
                giá trị trường này
                <br/>
                <br/>
                Hai chữ số sau cùng dùng cho số thập phân, được căn phải và điền số không vào trước
                nếu không đủ độ dài.
                <br/>
                <br/>
                Các trường liên quan cũng phải có mặt trong thông điệp là DE #9 và DE #50.
                <br/>
                <br/>
                Ví dụ: Chẳng hạn để biểu diễn US$ 423.92, trường này có giá trị “000000042392”.
                <br/>
                <br/>
                Trường này được điền bởi NAPAS trong các giao dịch tài chính mà NAPAS được quyền
                quyết định.
                <br/>
                <br/>
                Nếu là các giao dịch nội địa thì giá trị của trường này sẽ bằng với giá trị của DE #4.
            </p>
            <h5 id="title"><strong>#6 Cardholder Billing Amount</strong></h5>
            <p className="content-1">
                Định dạng: n-12
                <br/>
                <br/>
                Độ dài: 12 bytes
                <br/>
                <br/>
                Trong trường hợp một giao dịch xảy ra tại điểm chấp nhận thẻ sử dụng đơn vị tiền tệ
                khác đơn vị tiền tệ của chủ thẻ, trường này sẽ biểu diễn số tiền được thanh toán tới chủ
                thẻ trong đơn vị tiền tệ tại tài khoản của chủ thẻ.
                <br/>
                <br/>
                Số tiền được thanh toán tới chủ thẻ bằng số tiền quyết toán (DE #5) * Tỷ giá chuyển đổi
                tiền tệ từ đồng tiền quyết toán sang đồng tiền thanh toán (DE #10), cộng thêm tiền phí
                dịch vụ mà NHPH có thể thu đối với chủ thẻ.
                <br/>
                <br/>
                Trường giá trị tương đương (Equivalent Amount) được điền vào với các giá trị “0” và
                được căn phải. Chú ý, theo mặc định hai số bên phải được dành cho số thập phân.
                <br/>
                <br/>
                Các trường liên quan cũng phải có mặt trong thông điệp là DE #10 và DE #51.
                <br/>
                <br/>
                Chẳng hạn để biểu diễn US$ 423.92, trường này có giá trị “000000042392”.
            </p>
            <h5 id="title"><strong>#7 Transmission Date and Time</strong></h5>
            <p className="content-1">
                Định dạng: n-10, MMDDhhmmss
                <br/>
                <br/>
                Độ dài: 10 bytes
                <br/>
                <br/>
                Trường này được đòi hỏi trong tất các các thông điệp để biểu diễn thời gian và ngày
                tháng truyền theo định dạng GMT + 0 ngay khi giao dịch bắt đầu được gửi đi trong hệ
                thống mạng chuyển tiền điện tử. Một khi trường này đã được thiết lập, trường này sẽ
                được duy trì không đổi cho đến khi kết thúc một giao dịch.
                <br/>
                <br/>
                Một ví dụ để biểu diễn ngày mùng 6 tháng 5 vào hồi 2 giờ 30 phút 37 giây PM thì trường
                này sẽ có giá trị là : “0506143037”.
            </p>
            <h5 id="title"><strong>#9 Settlement Conversion Rate</strong></h5>
            <p className="content-1">
                Định dạng: n-8
                <br/>
                <br/>
                Độ dài: 8 bytes
                <br/>
                <br/>
                Được dùng để chuyển đối từ số tiền giao dịch sang số tiền quyết toán. Giá trị của trường
                này được căn phải và không có điểm thập phân. Số của thập phân được chỉ ra tại số
                bên trái nhất, các số từ 2 đến 8 chỉ ra tỷ lệ chuyển đổi.Chẳng hạn, giá trị của trường này
                bằng 71212345 sẽ chỉ ra tỷ lệ chuyển đổi là 0.1212345.
                <br/>
                <br/>
                Trường này được điền bởi NAPAS.
                <br/>
                <br/>
                Các trường liên quan cũng phải có mặt trong thông điệp là DE #4, DE #5 và DE #50.
            </p>
            <h5 id="title"><strong>#10 Cardholder Billing Conversion Rate</strong></h5>
            <p className="content-1">
                Định dạng: n-8
                <br/>
                <br/>
                Độ dài: 8 bytes
                <br/>
                <br/>
                Trong trường hợp nơi giao dịch xảy ra có đơn vị tiền tệ khác đơn vị tiền tệ của chủ thẻ,
                trường này sẽ biểu diễn tỷ giá được sử dụng để chuyển đổi từ số tiền quyết toán sang
                số tiền thanh toán của chủ thẻ. Kết quả của việc chuyển đổi được chỉ ra trong DE #6
                (Cardholder Billing Amount).
                <br/>
                <br/>
                Trường này được biểu diễn với định dạng là “xnnnnnn”, trong đó x là số từ “0” đến “7”
                để chỉ ra số vị trí của hệ thập phân có được bên phía phải.
                <br/>
                <br/>
                Chẳng hạn để biểu diễn tỉ giá là 7.123890 thì trường này sẽ có giá trị bằng “67123890”.
            </p>
            <h5 id="title"><strong>#11 System Trace Audit Number</strong></h5>
            <p className="content-1">
                Định dạng: n-6
                <br/>
                <br/>
                Độ dài: 6 bytes
                <br/>
                <br/>
                Trường dữ liệu này được đòi hỏi trong tất cảc các thông điệp. Mỗi giao dịch được gắn
                một giá trị lưu vết duy nhất và giá trị này được sinh ra từ bên chấp nhận thẻ/điểm thiết
                bị đầu cuối. Một điều đặc biệt lưu ý đó là số này không đủ để chỉ định tính duy nhất của
                một giao dịch bởi vì các hệ thống chuyển mạch có thể chấp nhận các thông điệp từ rất
                nhiều hệ thống chuyển mạch khác nhau nên có khả năng xảy ra một vài giao dịch trùng
                lặp số lưu vết trên hệ thống. Trường này sẽ được duy trì một cách không thay đổi đối
                với chu kỳ thông điệp (kết thúc một giao dịch).
                <br/>
                <br/>
                Trường này sẽ được căn phải và điền với các giá trị “0”.
            </p>
            <h5 id="title"><strong>#12 Local Transaction Time</strong></h5>
            <p className="content-1">
                Định dạng: n-6, hhmmss
                <br/>
                <br/>
                Độ dài: 6 bytes
                <br/>
                <br/>
                Trường này biểu diễn thời gian địa phương tại điểm chấp nhận thẻ đầu cuối khi giao
                dịch xảy ra. Trường này được đòi hỏi trong tất cả các giao dịch tài chính (02xx).
                <br/>
                <br/>
                Ví dụ: nếu muốn biểu diễn 5:14:53 PM thì giá trị của trường thời gian giao dịch địa
                phương sẽ bằng “171453”.
            </p>
            <h5 id="title"><strong>#13 Local Transaction Date</strong></h5>
            <p className="content-1">
                Định dạng: n-4, MMDD
                <br/>
                <br/>
                Độ dài: 4 bytes
                <br/>
                <br/>
                Trường này biểu diễn ngày tháng địa phương tại điểm chấp nhận đầu cuối khi giao dịch
                diễn ra. Trường này đòi hỏi trong tất cả các giao dịch tài chính (02xx).
                <br/>
                <br/>
                Ví dụ: nếu muốn biểu diễn ngày 18 tháng 3 thì giá trị của trường ngày tháng giao dịch
                địa phương sẽ bằng “0318”
            </p>
            <h5 id="title"><strong>#14 Expiration Date</strong></h5>
            <p className="content-1">
                Định dạng: n-4, YYMM
                <br/>
                <br/>
                Độ dài: 4 bytes
                <br/>
                <br/>
                Trường này được sử dụng để biểu diễn ngày hết hạn của thẻ. Trường này phải có nếu
                như bộ phận chấp nhận thẻ không thu nhận được các thông tin trên Track 1 hoặc Track
                2. Nếu như các Track 1 hoặc Track 2 được thu nhận bởi thiết bị chấp nhận thẻ thì
                trường này là trường mang tính không bắt buộc.
                <br/>
                <br/>
                Trường này sẽ biểu diễn ngày mà sau đó thẻ sẽ hết hiệu lực.
                <br/>
                <br/>
                Ví dụ: nếu thẻ sẽ hết hiệu lực vào tháng 07 năm 2004 thì giá trị trường ngày hết hiệu
                lực của thẻ sẽ bằng “0407”.
            </p>
            <h5 id="title"><strong>#15 Settlement Date</strong></h5>
            <p className="content-1">
                Định dạng: n-4, YYMM
                <br/>
                <br/>
                Độ dài: 4 bytes
                <br/>
                <br/>
                Đối với hệ thống chuyển mạch thẻ của NAPAS, hệ thống sẽ hoạt động xử lý đối với tất
                cả các ngày trong tuần (24x7) bao gồm cả ngày nghỉ, ngày lễ.
                <br/>
                <br/>
                NAPAS sẽ xác định giá trị của ngày quyết toán và điền vào trường settlement date
                DE#15 cho tất cả các giao dịch được gửi đến và gửi đi từ NAPAS. Bất kỳ giá trị nào
                được điền từ phía Ngân hàng sẽ được thay thế bằng ngày settlement date được NAPAS
                quy định.
                <br/>
                <br/>
                Ví dụ: nếu ngày thanh toán là ngày 12 tháng 4 thì giá trị trường ngày tháng thanh toán
                sẽ bằng “0412”
            </p>
            <h5 id="title"><strong>#18 Merchant Category Code</strong></h5>
            <p className="content-1">
                Định dạng: n-4
                <br/>
                <br/>
                Độ dài: 4 bytes
                <br/>
                <br/>
                Trường này được sử dụng để biểu diễn loại thiết bị cung cấp dịch vụ khi tạo ra giao dịch
                yêu cầu. NAPAS sử dụng giá trị này để xác định tính hợp lệ của giao dịch được yêu
                cầu. Bảng mã Merchant Category Code (MCC) sẽ đưa ra giá trị đối với từng loại thiết bị
                giao dịch khi được áp dụng.
            </p>
            <h5 id="title"><strong>#19 Acquiring Institution Country Code</strong></h5>
            <p className="content-1">
                Định dạng: n-3
                <br/>
                <br/>
                Độ dài: 3 bytes
                <br/>
                <br/>
                Trường này chứa mã quốc gia tại bên chấp nhận thẻ, đây là một tổ chức tài chính có
                trách nhiệm đối với quản lý các merchant hoặc ATM.
                <br/>
                <br/>
                Giá trị của trường này phải tuân theo chuẩn ISO 3166 về mã quốc gia.
            </p>
            <h5 id="title"><strong>#22 Point-Of-Service Entry Mode</strong></h5>
            <p className="content-1">
                Định dạng: n-3
                <br/>
                <br/>
                Độ dài: 3 bytes
                <br/>
                <br/>
                Trường này chỉ định phương thức số PAN được thu nhận cũng như khả năng nhập vào
                số PIN tại điểm chấp nhận thẻ.
            </p>
        </div>
    )
}
export default ISOtext