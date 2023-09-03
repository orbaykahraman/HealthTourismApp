# HealthTourismApp

Uygulamada Spring Security with JWT kullanılmıştır ve method level security eklenmiştir.
Mapper işlemleri için MapStruct kütüphanesi kullanılmıştır.
Documentation için SwaggerUI kullanılmıştır.
Exception'ların daha anlaşılır ve düzgün olması açısından GlobalExceptionHandler oluşturulup kendi oluşturduğumuz exceptionlar buradan yönetilmiştir.
Validasyonlar için spring-boot-starter-validation kullanılmıştır.

Öncelikle Register olmak için ;

/auth/register ' a POST ile istek atılmalıdır.Istekte girilmesi zorunlu alanlar name(username'e eşdeğer), nameSurname,email ve passworddur.
Opsiyonel olan alanlar ise roles,age ve gender'dir.
Roles olarak -> ROLE_ADMIN, ROLE_PATIENT ya da ROLE_DOCTOR girilmelidir.Eğer requestte rol girilmezse otomatik olarak ROLE_PATIENT atanır.
Register sırasında role'e göre kullanıcı için bir patient ya da doctor oluşturulup(ayni id ile) o da tabloya kayıt edilir.
Böylece her kullanıcı sadece 1 patient ya da doctor'a sahip olabilir.

Daha sonra authentica için ; /auth/authenticate 'e POST Request ile username ve password gönderilir.Buradaki username, kayıt sırasındaki name'e eşittir.
Bu 2 endpoint authenticate olmadan herkes tarafından erişilebilir durumdadır.

Authenticate başarılı olursa response olarak JWT token dönmektedir.Bu token 24 saat geçerlidir.

Program akışı ;
Admin uygulamaya hospital,hotel,airplane ekler.Bunları ekleme yetkisi sadece adminde vardir.
Doktorlar ve hastalar sisteme kayıt olur.
Doktorlar kendilerini bulunduğu hastaneye kayıt eder. (/doctor/{hospitalId}/{doctorId}) endpointi ile.

Buradan sonra hastalar /hospital endpointine GET requesti ile tüm hastaneleri listeler ve buradan Location'a göre kendisine en uygun hastaneyi bulur.
ya da /hospital/location endpoint'ine body kısmına "location" : "xxx" yaparak Location'a göre listeleme yapabilir.

Buradan kendisine uygun doktoru belirler.

Daha sonra /appointment endpoint'ine appointmentDate ve doctorId ile istek atıp appointment oluşturur.
Ve aynı işlemleri uçak ve otel için uygular.
Önce otel işlemi için otelleri listeler ve locationa göre kendisine uygun oteli seçip o otelin idsi ile HotelReservation oluşturur.
Eğer bunu yapan hastanın appointment'ı yoksa hotel reservation yapmasına izin verilmez.

Otel reservasyonundan sonra uçakları listeleyip destination'ına göre kendisine uygun olanı seçip o uçağın idsi ile FlightInformation oluşturur.
Eğer uçakta yer kalmadiysa hata fırlatır işleme izin vermez.
Eğer appointment ve hotel reservation yapılmadıysa uçak reservasyonuna izin verilmez.

Tüm bunları başarıyla yaptıktan sonra,(şuan reservasyon ve appointment statusleri waiting durumdadır) TravelPlan oluşturmalıdır.
Bunun için oluşturduğu appointmentId,hotelReservationId,flightInformationId ile /plan endpointine istek atıp travel plan oluşturur.
Burda kullanıcının appointment,hotelReservation,flightInformation'ı oluşturup oluşturmadığı kontrolü yapılır.
Kontrollerden geçince travelPlan dbye kaydedilir.
Bundan sonra ilgili appointment,hotelReservation,flightInformation status=approved olarak güncellenir.

CheckerService ;
Appointment,FlightInformation ve HotelReservation'ların dbde olup olmadığını kontrol eden servistir.
Ayrıca zamanlanmış(@Scheduled) metodlar vardır.

Bu metodlardan ilki checkAndCancelAppointments()'dir.30 saniyede bir çalışan ve appointment,hotelReservation,flightInfo tablolarından Status = Waiting olan kayıtları alıp creationDate'leri 10dakikayı geçmişse bunları tabloda status = cancelled olarak günceller.
Yani kullanıcı appointment oluşturduktan sonra 10 dakika içinde reservasyonları tamamlayıp travelplan oluşturmazsa bu rezervasyonlar status = waiting'de kalacağı için dbden silinir ve iptal olmuş olur.

İkinci zamanlanmış metod:handleStatus()'dir.2 saatte bir çalışır.Appointment,Flight ve Reservationları travelplan'dan alıp
kontrol edip eğer date'leri geçtiyse status = finished yapar.
Bu sayede appointment'lar finish olduğu için doktorlar appointment notes girebilir.

Doktorların appointment note girmesi için : /appointment/{appointmentId} endpointine "appointmentNotes" : "xxx" şeklinde istek atması gerekmektedir,ancak appointment'ın statusu = finished olması şarttır.


