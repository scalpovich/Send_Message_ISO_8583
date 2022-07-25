package com.opw.financemessage.util;

import org.springframework.stereotype.Component;

@Component
public class ReadRespondCode {
    public String read(String code){
        try {
            switch (code) {
                case "00":
                    return "Giao dịch thành công";
                case "01":
                    return "Chủ thẻ nên liên hệ với Ngân hàng phát hành thẻ";
                case "03":
                    return "Mã đại lý không hợp lê";
                case "04":
                    return "Thẻ của bạn đã bị thu hồi";
                case "05":
                    return "Lỗi trong xử lí giao dịch";
                case "12":
                    return "Giao dịch không hợp lệ";
                case "13":
                    return "Giá trị giao dịch không hợp lệ";
                case "14":
                    return "Số thẻ không hợp lệ";
                case "15":
                    return "Không tìm thấy ngân hàng phát hành thẻ";
                case "21":
                    return "Chủ thẻ cần kích hoạt lại thẻ";
                case "25":
                    return "Không có giao dịch gốc, đề nghị liên lạc với ngân hàng";
                case "30":
                    return "Lỗi định dạng thông điệp";
                case "34":
                    return "Thẻ giả mạo, thu hồi thẻ";
                case "39":
                    return "Tài khoản không hợp lệ";
                case "41":
                    return "Thẻ đã được khách hàng thông báo mất, thu hồi thẻ";
                case "43":
                    return "Thẻ đã được khách hàng báo là mất cắp";
                case "51":
                    return "Số dự tài khoảng không đủ";
                case "53":
                    return "Tài khoản tiết kiệm không hợp lệ";
                case "54":
                    return "Thẻ hết hạn";
                case "55":
                    return "Sai PIN/TOP";
                case "57":
                    return "thẻ không thể thực hiện giao dịch này";
                case "58":
                    return "Ngân hàng phát hành không cho phép thẻ này được thực hiện tại thiết bị hiện tại";
                case "59":
                    return "Nghi ngờ giả mạo từ chối giao dịch";
                case "61":
                    return "Số tiền giao dịch vượt quá giới hạn cho phép";
                case "62":
                    return "Lỗi trong việc xử lý liên quan đến an toàn bảo mật";
                case "64":
                    return "Khối lượng giao dịch không chính xác so với giao dịch gốc";
                case "65":
                    return "Vượt quá giới hạn số lần rút tiền";
                case "68":
                    return "Giao dịch bị tham out";
                case "75":
                    return "Vượt quá số lần cho phép nhập sai";
                case "76":
                    return "Tài khoản không hợp lệ";
                case "84":
                    return "Xác thực giá trị ARQC lỗi";
                case "85":
                    return "Ngân hàng phát hành từ chối No CVM, yêu cầu nhập pin";
                case "90":
                    return "Hệ thống đang trong quá trình xử lí cuối ngày, đề nghị thực hiện sau";
                case "91":
                    return "Không thể kết nối được với ngân hàng phát hành, để nghị thử lại sau";
                case "92":
                    return "Kết nối mạng không sẵn sàng";
                case "94":
                    return "Giao dịch bị lặp lại";
                case "96":
                    return "Hệ thống chuyển mạch NAPAS có sự cố";
                default:
                    return "Sai kết quả";
            }
        }
        catch(NullPointerException e)
        {
            return "NullPointerException Caught";
        }
    }
}
