# Message-ISO-8583
## Quick Transaction
### Balance
Bắt đâu từ Controller Balance. Trong controller có hàm ```sendMessage```, hàm này có đối số đầu
vào là một List DataElement nhận về từ người dùng nhập vào. Trong hàm này chúng gọi đến 
service của giao dịch Balance là ```MessageBalanceService```, và dùng service này để 
gửi một tin nhắn đi và trả về chuỗi là giá trị của trường 63. Service gọi đến hàm ```send``` 
để gửi tin nhắn. Trong hàm ```send``` này, ta chèn thêm một số trường thời gian nữa như f7, f12, f13
và f63 và sắp xếp lại danh sách các Data Element này theo thứ tự của các trường. Cuối cùng là
gọi hàm ```sendMessage``` là phương thức của lớp cha mà nó kết thừa ```ImplMessageService```. Service
```ImplMessageService``` này có hai hàm chính là ```sendMessage``` và ```getMessage```, và có thêm một hàm
``getMessageAuto`` 

Hàm ```sendMessage``` nhận đối số là một danh sách các Data Element và 
trường 63. Hàm này có các nhiệm vụ sau:
- Kiểm tra xem chương trình đã kết nối socket chưa
- Lưu danh sách các dataElement xuống database
- Chuyển các Data Element kia thành một Object ``MessageISO``
- Gọi Component ``processor`` để chuyển Object MessageISO sang là một tin nhắn raw.
- Lưu message raw xuống database
- Gọi socket và gửi tin nhắn raw đi
- Trả về là giá trị trường 63

Hàm ```getMessage``` nhận đối số trường 63 và thời gian hàm được gọi.
Hàm này có nhiệm vụ sau.
- Kiểm tra tin nhắn đã được nhận về chưa, nếu chưa thì đã quá thời gian đợi chưa
- Nếu nhận được về rồi thì đọc mã responde của tin nhắn bằng lớp ``ReadRespondCode``
- Trả về kết quả của mã respond code

Hàm ```getMessageAuto```

Cuối cùng là dùng service và gọi hàm ```getMessageByField63``` với đối số là f63 và thời gian bắt
đâu chạy, để đọc được respond code của tin nhắn đó. 