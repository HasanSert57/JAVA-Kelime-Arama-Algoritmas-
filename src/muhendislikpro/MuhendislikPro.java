package muhendislikpro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MuhendislikPro {

    public static int ascii(String satir)                        //ascii hesabı yapılıyor
    {
        String cevir = satir.toLowerCase();                                              // Dosyadan verileri kucuk harfe cevirerek al
        int kelime_toplam = 0;
        for (int a = 0; a < cevir.length(); a++)                                    //Kelimenin ascii değerini hesaplatma işlemini yaptık 
        {
            char c_karakter = cevir.charAt(a);
            int ascii_no = (int) c_karakter;
            kelime_toplam += ascii_no * Math.pow((a + 1), 4);
        }
        return kelime_toplam;                                           //ascii methodunu int tipinde kelime_toplam degerini dödürme işlemini gerçekleştirdik
    }

    public static String[] eksiltili_arama(String satir)                    //Girilen kelime listede yoksa harf harf eksiltilerek arama işlemi gerçekleştiriliyor 
    {
        String kelime = "";                                                                 //kelime adında boş bir string oluşturduk
        String dizi[] = new String[satir.length()];                                     //Kullanıcıdan aldığımız kelimenin boyutu kadarlık bir String dizisi açtık
        int sayac = satir.length() - 1;                                             //sayac adında bi int dosyası açtık ,girilen kelimenin 1 eksiğini atadık 
        while (sayac >= 0)                                               //sayacın 0dan buyuk oldugunda donen br while dongusu açtık
        {
            for (int i = 0; i < satir.length(); i++)                                        //satır uzunlugu buyuklugunde for dongusu acıldı
            {
                if (i != sayac) {
                    kelime += satir.charAt(i);                                            //Tek tek yeni oluşan kelimeler bulundu
                }
            }
            dizi[sayac] = kelime;                                                                    //diziye yeni degerleri atıldı
            kelime = "";                                                             //kelime degeri boşaltıldı
            sayac--;                                                                     //sayac 1 azaltıldı
        }
        return dizi;                                   //Sonuc olarak yeni oluşturduğumuz dizi döndürdük
    }

    public static String[] komsu_arama(String satir)                                        //Girilen kelime listede yoksa komşuların yer değiştilmesiyle oluşan kelimeler bulundu
    {
        String kelime = "";
        String dizi[] = new String[satir.length() - 1];
        char karakterDizi[] = new char[satir.length()];
        int sayac = satir.length() - 1;
        char degisken;
        while (sayac > 0) {
            for (int k = 0; k < karakterDizi.length; k++) {
                karakterDizi[k] = satir.charAt(k);                                      //diziye satirdkaki harfleri tek tek attık 
            }
            degisken = karakterDizi[sayac];                                             //gecici degiskene harf atadık
            karakterDizi[sayac] = karakterDizi[sayac - 1];                          //yer değişimi yapıldı
            karakterDizi[sayac - 1] = degisken;                                             //gecici kullanıldı
            for (int j = 0; j < satir.length(); j++) {                                      //satır boyutunda for dongusu acıldı
                kelime += karakterDizi[j];                                                  //yeni kelime oluşturuldu
            }
            dizi[sayac - 1] = kelime;                                                //diziye yeni kelimeler yerleştirildi
            kelime = "";                                                                //kelime boşaltıldı
            sayac--;                                                                    //sayac 1 azaltıldı
        }
        return dizi;                                                                                 //Oluşan dizi geri döndürüldü
    }
    public static String path = "Kelimeler.txt";                                          //Kelşmelerin bulundugu dosya global bir şekilde String degere atılıyor

    public static String[] dosya_okuma()                                                //Kelimelerin bulunduğu dosya okutuldu
    {
        ArrayList<String> arraylist = new ArrayList<String>();                                              //ArrayList oluşturuldu
        try {
            FileReader fr = new FileReader(path);                                           //Yol belirtildi
            BufferedReader br = new BufferedReader(fr);                                                      //Okuma fonksiyonu çalıştırıldı
            String satir;
            int sayac = 0;
            while ((satir = br.readLine()) != null)                                                 //satir null oluncaya kadar donduruldu
            {
                String cevir = satir.toLowerCase();                                                   //kelime nin küçük harflere çevrilmiş hali atandı                  
                arraylist.add(cevir);                                                                           //arrayliste oluşan kelime eklendi
                sayac++;
            }
            br.close();                                                                  //dosya kapatıldı
        } catch (Exception E)                               //Olası ihtimallere karşı dosyanın var olmama durumuna karşı hata mesajı verşldi
        {
            System.out.println("Dosya bulanamadi ...");                             //Hata mesajı
        }
        String liste[] = new String[arraylist.size()];        //arraylistin boyutu kadar bir String dizisi oluşturuldu,geri dönen deger String[] dizi olduğu için
        for (int i = 0; i < arraylist.size(); i++) {          //arraylist teki degerlerin String dizisine ataması gerçekleşti
            liste[i] = arraylist.get(i);                       //atama
        }

        return liste;                                                           //String dizisi geri döndürülüyot
    }

    public static int[] asciiListesi()                                       //Kelimelerin ascii degerlerine gore mod alınarak ascii dizisi oluşturuluyor
    {
        int[] secili_dizi = new int[102];                                           //Ascii degerlerinin modu alınıpp yerleştirilecekleri dizi
        String[] dizi = new String[dosya_okuma().length];                          //dosya_okuma methodundan donen listenin bouyutu kadar bir dizi döndürülüyor
        dizi = dosya_okuma();                                                               //dosya_okuma dan donen liste yeni oluşturudumuz diziye aktarılıyor
        int kelime_toplam = 0;                                                               //kelime_toplam ilk degeri 0 atılıyor
        for (int i = 0; i < dizi.length; i++)                                                            //dizi boyutu kadar donen bir for dongusu
        {
            kelime_toplam = ascii(dizi[i]);                      //Bu for dongüsünde bütün kelimeler tek tek ascii methodunu gönderilerk assci deger toplamları  
            for (int j = 0; j < secili_dizi.length; j++)           //bulunarak 202 ye gore modları alınyor ve sonrasında oluşturdugumuz diziye yerleştiriliyorlar
            {                                           //eger dizide o alan doluysa quadratic yontemine gore işlemler gerçekleştirilerek diziye ekleme yapılmaya
                int mod = kelime_toplam % 101;                                                     //çalışılıyor.Son olarak oluşan dizi geri dödürülüyor
                if (secili_dizi[mod] == 0) {
                    secili_dizi[mod] = kelime_toplam;
                    break;
                } else {
                    int kalan = (mod + (j * j)) % 101;
                    if (secili_dizi[kalan] == 0) {
                        secili_dizi[kalan] = kelime_toplam;
                        break;
                    }
                }
            }
            kelime_toplam = 0;
        }
        return secili_dizi;
    }

    public static String[] isimListesi() //ascii listesinde yapılabilirdi fakat geriye 2 tane dizi döndüremediğimizden dolayı 
    {                                                      //bu methodu da oluşturuyoruz.Bu methodda da aynı şekilde işlemler gerçekleştiriliyor.
        String myList[] = new String[102];                  //Son olarak bundanda isimlerin listesi return ediliyor
        String[] dizi = new String[dosya_okuma().length];
        dizi = dosya_okuma();
        int kelime_toplam = 0;
        for (int i = 0; i < dizi.length; i++) {
            kelime_toplam = ascii(dizi[i]);
            for (int j = 0; j < myList.length; j++) {
                int mod = kelime_toplam % 101;
                if (myList[mod] == null) {
                    myList[mod] = dizi[i];
                    break;
                } else {
                    int kalan = (mod + (j * j)) % 101;
                    if (myList[kalan] == null) {
                        myList[kalan] = dizi[i];
                        break;
                    }
                }
            }
            kelime_toplam = 0;
        }

        return myList;

    }

    public static void arama() //Arama yapacağımız method
    {
        int[] secili_dizi = new int[102];
        String myList[] = new String[102];

        myList = isimListesi();
        secili_dizi = asciiListesi();
        ArrayList array = new ArrayList();
        System.out.println("**********ARAMA BOLUMU*************");
        System.out.print("Aranacak adi giriniz : ");
        Scanner input = new Scanner(System.in);    //Bu methodda kullanıcı bir kelime giriyor girilen kelime listede aratılıyor eger varsa ekrana mesaj yolllanıyor
        String metin;                          //yoksa kelime eksiltili_arama ve komsu_arama methodlarına yollanıyor ve donen degerler tekrardan listede 
                                                //varmı diye kontrol
        metin = input.next();                     //ediliyor eger bu iki methoddan gelen sonuçlara gore de listede yoklarsa hiç bir şekilde kelimenin olmadıgı yönünde mesaj gönderiliyor.
        int kelime_toplam = 0;
        String cevir = metin.toLowerCase();
        String dizi[] = new String[cevir.length()];
        for (int i = 0; i < myList.length; i++) {
            array.add(myList[i]);
        }
        kelime_toplam = ascii(cevir);
        String[] eksiltili_dizi = new String[cevir.length()];
        String[] komsu_dizi = new String[cevir.length() - 1];
        boolean deger = false;
        for (int i = 0; i < secili_dizi.length; i++) {
            if (secili_dizi[i] == kelime_toplam)                                                                    // kelimenin ascci degeriyle girilen kelimenin ascii degeri karşılaştırılıyor
            {                                                                                                           // eger uygunsa listede bu kelime aynı isimle de bulunuyormu diye kontrol ediliyor

                System.out.println(" Girdiginiz *" + metin + "*  kelimesi metin dosyasinda bulunmaktadır...");          //kelime metin dosyasında her ihtimale karşı var
                deger = true;

            }
        }
        int sayac = 0;
        if (deger == false) {
            for (int i = 0; i < secili_dizi.length; i++) {
                eksiltili_dizi = eksiltili_arama(cevir);
                komsu_dizi = komsu_arama(cevir);
                for (int j = 0; j < eksiltili_dizi.length; j++) {
                    if (ascii(eksiltili_dizi[j]) == secili_dizi[i]) {

                        System.out.println("Girdiğiniz *" + metin + "* kelimesi metin dosyasında *" + eksiltili_dizi[j] + "* olarak bulunmuştur");
                        sayac++;        //Sayac kullanmamızın sebebi kelimeni listede hiç bulunamama durumunda kontrol sayacı olarak görev almıştır
                        break;
                    }
                }
                for (int j = 0; j < komsu_dizi.length; j++) {
                    if (ascii(komsu_dizi[j]) == secili_dizi[i]) {

                        System.out.println("Girdiğiniz *" + metin + "* kelimesi metin dosyasında *" + komsu_dizi[j] + "* olarak bulunmuştur");
                        sayac++;        //yukarıdaki sayac ile aynı.Bu sayac eger 0'da kalmassa demekki hiç bu if lere girmemişitir demektir
                        break;          //İşlemi sonlandırma kısmı sayaca gerek kalmıyor gibi gözüksede olmadığında hata veriyor

                    }
                }
            }
            if (sayac == 0) {
                System.out.println("*" + metin + "* ile alakalı metin dosyasında benzer kelime bulunamamıştır ...");   //sayacın 0 ve kelimenin listede hiç olmama durumunda çalışyor
            }
            sayac = 0;
        }
    }                                        //Yerleştirdiğimiz kelimelerin indisi,kelime adı,ascii karşılıklarını ekrana yazdırma işlemi

    public static void listeYaz() {
        String myList[] = new String[102];
        myList = isimListesi();
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] != null) {
                System.out.println("[" + i + "]" + ":" + myList[i] + " " + ascii(myList[i]));
            }
        }
    }

    public static void main(String[] args) {
        listeYaz();                                                                     //listeyaz() methodu çağırılıyor
        boolean deger = true;                                                           //true , false degerlerini alan bir boolean tipinde degişken oluşturuluyor
        Scanner oku = new Scanner(System.in);                                           //Scannner kutuphanesi
        try //Klavyeden true , false dışında deger girme durumu tespit etme 
        {
            while (deger == true) //deger == true olduğu sürece tekrar etsin
            {
                arama();                                                                 //arama() methodu çağırılıyor
                System.out.println("Arama yapmak İstiyormusunuz : (True/False)");         //Kullanıcıdan true ve ya false degeri girmesi isteniyor
                deger = oku.nextBoolean();                                                   //deger kullanıcıdan gelen mesaj atılıyor
            }
        } catch (Exception E) {
            System.out.println("İstek Dışında deger girildi");                                  //Hata mesajı veriliyor
        }
    }
}
