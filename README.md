#1. Danh sách nhóm:
    Đoàn Minh Đức - 2310767
    Trần Phương Đỉnh - 2310744
#2. URL Web Service:
    https://student-management-wyt7.onrender.com/students
#3. Cách chạy dự án:
  ##3.1 Clone repository
  '''bash
  git clone <repository-url>
  '''
  ##3.2 Tạo file .env 
  Tạo file .env trong thư mục student-management với các biến môi trường sau:
  '''bash
  POSTGRES_HOST=localhost
  POSTGRES_PORT=5432
  POSTGRES_DB=student_management
  POSTGRES_USER=<Your DB Username>
  POSTGRES_PASSWORD=<Your DB Password>
  SPRING_DATASOURCE_URL=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB}
  #SPRING_DATASOURCE_URL=<Your Connection String> nếu sử dụng neon
  SPRING_DATASOURCE_USERNAME=${POSTGRES_USER}
  SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD}
  '''
  ##3.2 Tạo file .env 
  Tạo file .env trong thư mục student-management với các biến môi trường sau:
  '''bash
  POSTGRES_HOST=localhost
  POSTGRES_PORT=5432
  POSTGRES_DB=student_management
  POSTGRES_USER=<Your DB Username>
  POSTGRES_PASSWORD=<Your DB Password>
  SPRING_DATASOURCE_URL=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB}
  #SPRING_DATASOURCE_URL=<Your Connection String> nếu sử dụng neon
  SPRING_DATASOURCE_USERNAME=${POSTGRES_USER}
  SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD}
  '''
  ##3.3 Chạy chương trình
  '''bash
  ./mvnw dependency:resolve

  ./mvnw spring-boot:run
  '''
#4. Trả lời câu hỏi:
  ##4.1 Q1: Ràng buộc Khóa Chính (Primary Key)
    Khi cố tình insert một sinh viên có id trùng với bản ghi đã tồn tại, Database báo lỗi UNIQUE constraint
    failed. Lý do là cột khóa chính phải duy nhất để đảm bảo mỗi bản ghi được định danh không trùng
    lặp; nếu cho phép trùng, các thao tác cập nhật, xóa, hoặc liên kết khóa ngoại sẽ không còn xác định được
    đúng bản ghi.
  ##4.2 Q2: Toàn vẹn dữ liệu (Constraints)
    Khi insert một sinh viên nhưng để trống cột name (giá trị NULL), Database không báo lỗi nếu cột này
    không được khai báo NOT NULL. Sự thiếu chặt chẽ này có thể gây lỗi khi code Java đọc dữ liệu, ví dụ
    hiển thị tên sinh viên bị rỗng, phát sinh NullPointerException khi xử lý chuỗi, hoặc làm sai lệch logic
    nghiệp vụ.
  ##4.3 Q3: Cấu hình Hibernate
    Mỗi lần tắt ứng dụng và chạy lại mà dữ liệu trong Database bị mất thường do cấu hình Hibernate sử
    dụng chế độ tự động tạo lại schema (ví dụ hibernate.hbm2ddl.auto=create hoặc create-drop). Chế
    độ này sẽ xóa và tạo lại bảng khi ứng dụng khởi động (hoặc tắt), khiến dữ liệu cũ bị mất. Cần đổi sang
    update hoặc validate để giữ dữ liệu.
#5. Screenshot module:
