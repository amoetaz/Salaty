package app.com.salaty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SalaTiming implements Serializable
{
    @SerializedName("data")
    @Expose
    private ArrayList<Datum> data = new ArrayList<>();

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("status")
    @Expose
    private String status;

    public SalaTiming(){

    }

    public int getCode() { return this.code; }

    public void setCode(int code) { this.code = code; }

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

    public ArrayList<Datum> getData() { return this.data; }

    public void setData(ArrayList<Datum> data) { this.data = data; }


    public class Timings implements Serializable {
        @SerializedName("Fajr")
        private String Fajr;

        public String getFajr() { return this.Fajr; }

        public void setFajr(String Fajr) { this.Fajr = Fajr; }
        @SerializedName("Sunrise")
        private String Sunrise;

        public String getSunrise() { return this.Sunrise; }

        public void setSunrise(String Sunrise) { this.Sunrise = Sunrise; }
        @SerializedName("Dhuhr")
        private String Dhuhr;

        public String getDhuhr() { return this.Dhuhr; }

        public void setDhuhr(String Dhuhr) { this.Dhuhr = Dhuhr; }
        @SerializedName("Asr")
        private String Asr;

        public String getAsr() { return this.Asr; }

        public void setAsr(String Asr) { this.Asr = Asr; }
        @SerializedName("Sunset")
        private String Sunset;

        public String getSunset() { return this.Sunset; }

        public void setSunset(String Sunset) { this.Sunset = Sunset; }
        @SerializedName("Maghrib")
        private String Maghrib;

        public String getMaghrib() { return this.Maghrib; }

        public void setMaghrib(String Maghrib) { this.Maghrib = Maghrib; }

        @SerializedName("Isha")
        private String Isha;

        public String getIsha() { return this.Isha; }

        public void setIsha(String Isha) { this.Isha = Isha; }

        @SerializedName("Imsak")
        private String Imsak;

        public String getImsak() { return this.Imsak; }

        public void setImsak(String Imsak) { this.Imsak = Imsak; }

        @SerializedName("Midnight")
        private String Midnight;

        public String getMidnight() { return this.Midnight; }

        public void setMidnight(String Midnight) { this.Midnight = Midnight; }

    }
    public class Date implements Serializable {
        @SerializedName("readable")
        private String readable;

        @SerializedName("timestamp")
        private String timestamp;


        public String getReadable() { return this.readable; }

        public void setReadable(String readable) { this.readable = readable; }

        public String getTimestamp() { return this.timestamp; }

        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        /*@Expose
        private Gregorian gregorian;

        public Gregorian getGregorian() { return this.gregorian; }

        public void setGregorian(Gregorian gregorian) { this.gregorian = gregorian; }
        @Expose
        private Hijri hijri;

        public Hijri getHijri() { return this.hijri; }

        public void setHijri(Hijri hijri) { this.hijri = hijri; }*/

    }
    public class Datum implements Serializable {
        @SerializedName("timings")
        private Timings timings;

        @SerializedName("date")
        private Date date;

        public Timings getTimings() { return this.timings; }

        public void setTimings(Timings timings) { this.timings = timings; }


        public Date getDate() { return this.date; }

        public void setDate(Date date) { this.date = date; }
        /*@Expose
        private Meta meta;

        public Meta getMeta() { return this.meta; }

        public void setMeta(Meta meta) { this.meta = meta; }*/

    }

    /*public class Weekday implements Serializable {
        private String en;

        public String getEn() { return this.en; }

        public void setEn(String en) { this.en = en; }

    }

    public class Month implements Serializable {
        private int number;


        public int getNumber() { return this.number; }

        public void setNumber(int number) { this.number = number; }

        private String en;

        public String getEn() { return this.en; }

        public void setEn(String en) { this.en = en; }
    }

    public class Designation implements Serializable {
        private String abbreviated;



        public String getAbbreviated() { return this.abbreviated; }

        public void setAbbreviated(String abbreviated) { this.abbreviated = abbreviated; }

        private String expanded;

        public String getExpanded() { return this.expanded; }

        public void setExpanded(String expanded) { this.expanded = expanded; }


    }

    public class Gregorian implements Serializable {
        private String date;




        public String getDate() { return this.date; }

        public void setDate(String date) { this.date = date; }

        private String format;

        public String getFormat() { return this.format; }

        public void setFormat(String format) { this.format = format; }

        private String day;

        public String getDay() { return this.day; }

        public void setDay(String day) { this.day = day; }

        private Weekday weekday;

        public Weekday getWeekday() { return this.weekday; }

        public void setWeekday(Weekday weekday) { this.weekday = weekday; }

        private Month month;

        public Month getMonth() { return this.month; }

        public void setMonth(Month month) { this.month = month; }

        private String year;

        public String getYear() { return this.year; }

        public void setYear(String year) { this.year = year; }

        private Designation designation;

        public Designation getDesignation() { return this.designation; }

        public void setDesignation(Designation designation) { this.designation = designation; }
    }
    public class Weekday2 implements Serializable {
        private String en;


        public String getEn() { return this.en; }

        public void setEn(String en) { this.en = en; }

        private String ar;

        public String getAr() { return this.ar; }

        public void setAr(String ar) { this.ar = ar; }

    }

    public class Month2 implements Serializable {
        private int number;



        public int getNumber() { return this.number; }

        public void setNumber(int number) { this.number = number; }

        private String en;

        public String getEn() { return this.en; }

        public void setEn(String en) { this.en = en; }

        private String ar;

        public String getAr() { return this.ar; }

        public void setAr(String ar) { this.ar = ar; }
    }

    public class Designation2 implements Serializable {
        private String abbreviated;




        public String getAbbreviated() { return this.abbreviated; }

        public void setAbbreviated(String abbreviated) { this.abbreviated = abbreviated; }

        private String expanded;

        public String getExpanded() { return this.expanded; }

        public void setExpanded(String expanded) { this.expanded = expanded; }
    }

    public class Hijri implements Serializable {
        private String date;

        public String getDate() { return this.date; }

        public void setDate(String date) { this.date = date; }

        private String format;

        public String getFormat() { return this.format; }

        public void setFormat(String format) { this.format = format; }

        private String day;

        public String getDay() { return this.day; }

        public void setDay(String day) { this.day = day; }

        private Weekday2 weekday;

        public Weekday2 getWeekday() { return this.weekday; }

        public void setWeekday(Weekday2 weekday) { this.weekday = weekday; }

        private Month2 month;

        public Month2 getMonth() { return this.month; }

        public void setMonth(Month2 month) { this.month = month; }

        private String year;

        public String getYear() { return this.year; }

        public void setYear(String year) { this.year = year; }

        private Designation2 designation;

        public Designation2 getDesignation() { return this.designation; }

        public void setDesignation(Designation2 designation) { this.designation = designation; }

        private ArrayList<String> holidays;

        public ArrayList<String> getHolidays() { return this.holidays; }

        public void setHolidays(ArrayList<String> holidays) { this.holidays = holidays; }
    }


    public class Params implements Serializable {
        private int Fajr;



        public int getFajr() { return this.Fajr; }

        public void setFajr(int Fajr) { this.Fajr = Fajr; }

        private int Isha;

        public int getIsha() { return this.Isha; }

        public void setIsha(int Isha) { this.Isha = Isha; }
    }

    public class Method implements Serializable {
        private int id;


        public int getId() { return this.id; }

        public void setId(int id) { this.id = id; }

        private String name;

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }

        private Params params;

        public Params getParams() { return this.params; }

        public void setParams(Params params) { this.params = params; }
    }

    public class Offset implements Serializable {
        private int Imsak;


        public int getImsak() { return this.Imsak; }

        public void setImsak(int Imsak) { this.Imsak = Imsak; }

        private int Fajr;

        public int getFajr() { return this.Fajr; }

        public void setFajr(int Fajr) { this.Fajr = Fajr; }

        private int Sunrise;

        public int getSunrise() { return this.Sunrise; }

        public void setSunrise(int Sunrise) { this.Sunrise = Sunrise; }

        private int Dhuhr;

        public int getDhuhr() { return this.Dhuhr; }

        public void setDhuhr(int Dhuhr) { this.Dhuhr = Dhuhr; }

        private int Asr;

        public int getAsr() { return this.Asr; }

        public void setAsr(int Asr) { this.Asr = Asr; }

        private int Maghrib;

        public int getMaghrib() { return this.Maghrib; }

        public void setMaghrib(int Maghrib) { this.Maghrib = Maghrib; }

        private int Sunset;

        public int getSunset() { return this.Sunset; }

        public void setSunset(int Sunset) { this.Sunset = Sunset; }

        private int Isha;

        public int getIsha() { return this.Isha; }

        public void setIsha(int Isha) { this.Isha = Isha; }

        private int Midnight;

        public int getMidnight() { return this.Midnight; }

        public void setMidnight(int Midnight) { this.Midnight = Midnight; }
    }

    public class Meta implements Serializable {
        @Expose
        private double latitude;


        public double getLatitude() { return this.latitude; }

        public void setLatitude(double latitude) { this.latitude = latitude; }
        @Expose
        private double longitude;

        public double getLongitude() { return this.longitude; }

        public void setLongitude(double longitude) { this.longitude = longitude; }
        @Expose
        private String timezone;

        public String getTimezone() { return this.timezone; }

        public void setTimezone(String timezone) { this.timezone = timezone; }
        @Expose
        private Method method;

        public Method getMethod() { return this.method; }

        public void setMethod(Method method) { this.method = method; }
        @Expose
        private String latitudeAdjustmentMethod;

        public String getLatitudeAdjustmentMethod() { return this.latitudeAdjustmentMethod; }

        public void setLatitudeAdjustmentMethod(String latitudeAdjustmentMethod) { this.latitudeAdjustmentMethod = latitudeAdjustmentMethod; }
        @Expose
        private String midnightMode;

        public String getMidnightMode() { return this.midnightMode; }

        public void setMidnightMode(String midnightMode) { this.midnightMode = midnightMode; }
        @Expose
        private String school;

        public String getSchool() { return this.school; }

        public void setSchool(String school) { this.school = school; }
        @Expose
        private Offset offset;

        public Offset getOffset() { return this.offset; }

        public void setOffset(Offset offset) { this.offset = offset; }
    }
*/


}



