package io.spring.batch.common;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;

public class CommUtil
{
    public static long setIptoLong(String ip)
    {
        String forthStr = "";
        int first = ip.indexOf(".");
        String firstStr = ip.substring(0, first);
        String elseStr1 = ip.substring(first+1);
        int second = elseStr1.indexOf(".");
        String secondStr = elseStr1.substring(0, second);
        String elseStr2 = elseStr1.substring(second+1);
        if(secondStr.length() == 1){
            secondStr = "00"+secondStr;
        }else if(secondStr.length() == 2){
            secondStr = "0"+secondStr;
        }
        int third = elseStr2.indexOf(".");
        String thirdStr = elseStr2.substring(0, third);
        String elseStr3 = elseStr2.substring(third+1);
        if(thirdStr.length() == 1){
            thirdStr = "00"+thirdStr;
        }else if(thirdStr.length() == 2){
            thirdStr = "0"+thirdStr;
        }
        int forth = elseStr3.indexOf(".");
        if(forth <0){
            forthStr = elseStr3;
            if(forthStr.length() == 1){
                forthStr = "00"+forthStr;
            }else if(forthStr.length() == 2){
                forthStr = "0"+forthStr;
            }
        }
        ip = firstStr+secondStr+thirdStr+forthStr;
        //System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        System.out.println("first : "+first);
        System.out.println("firstStr : "+firstStr);
        System.out.println("elseStr : "+elseStr1);
        //System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        System.out.println("second : "+second);
        System.out.println("secondStr : "+secondStr);
        System.out.println("elseStr2 : "+elseStr2);
        //System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        System.out.println("third : "+third);
        System.out.println("thirdStr : "+thirdStr);
        System.out.println("elseStr3 : "+elseStr3);
        //System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        System.out.println("forth : "+forth);
        System.out.println("forthStr : "+forthStr);
        //System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        System.out.println("ip : "+ip);
        //System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        long ipAddress = Long.parseLong(ip);
        return ipAddress;
    }
    /**
     * 현재(한국기준) 시간정보를 얻는다.                     <BR>
     * (예) 입력파리미터인 format string에 "yyyyMMddhh"를 셋팅하면 1998121011과 같이 Return.  <BR>
     * (예) format string에 "yyyyMMddHHmmss"를 셋팅하면 19990114232121과 같이
     *      0~23시간 타입으로 Return. <BR>
     *      String CurrentDate = CmicUtil.getKST("yyyyMMddHH");<BR>
     * @param    format      얻고자하는 현재시간의 Type
     * @return   str 		현재 한국 시간.
     */
    public static String getKST(String format)
    {
        //1hour(ms) = 60s * 60m * 1000ms
        int millisPerHour = 60 * 60 * 1000;
        SimpleDateFormat fmt= new SimpleDateFormat(format);

        SimpleTimeZone timeZone = new SimpleTimeZone(9*millisPerHour,"KST");
        fmt.setTimeZone(timeZone);

        long time=System.currentTimeMillis();
        String str=fmt.format(new java.util.Date(time));
        return str;
    }


    /*
     * 9일 이전이면 false(9일부터는 true)
     *
     * */
    public static boolean getKSTDate(String start){
        boolean dayflg = true;
        int stdt = 0 ;
        stdt = Integer.parseInt(start);

        SimpleDateFormat sf	= new SimpleDateFormat ("dd");
        Date date          = new Date();

        dayflg = stdt <=  Integer.parseInt(sf.format(date));
        return dayflg;
    }//end method


    /**
     * 현재(한국기준) 시간정보를 얻는다.                     	<BR>
     * informix전용의 DATE 관련자료형의 표기법을 따른다.		<BR>
     * 표기법은 yyyy-mm-dd 									<BR>
     * @param   N/A
     * @return   String 		yyyy/mm/dd형태의 현재 한국시간.             <BR>
     */
    public static String getKSTDate()
    {
        // Format the current time.

/*			SimpleDateFormat formatter	= new SimpleDateFormat ("yyyyy/MM/dd");
			Date currentTime_1          = new Date();

			return formatter.format(currentTime_1);
*/			return getDateFormat("yyyyy/MM/dd");
    }//end method


    /**
     * 현재(한국기준) 시간정보를 얻는다.                     	<BR>
     * informix전용의 DATE 관련자료형의 표기법을 따른다.		<BR>
     * 표기법은 yyyy.mm.dd 									<BR>
     * @param   N/A
     * @return   String 		yyyy.mm.dd형태의 현재 한국시간.             <BR>
     */
    public static String getKSTDateFormatDot(String data)	{
        if (data == null || data.length() != 8)//입력값이 8자리 아니면 들어온값 그대로 return
            return data;
        else if ( data.length() == 8){

            StringBuffer sb = new StringBuffer();
            sb.append(data.substring(0, 4));
            sb.append(".");
            sb.append(data.substring(4, 6));
            sb.append(".");
            sb.append(data.substring(6, 8));

            return sb.toString();
        }else{
            return "";
        }

    }//end method





    /**
     * 현재 날짜 기준으로 입력한 날짜보다 크다면 입력한 현재달을
     * @param dayInt 어느날짜를 기준으로 할건지
     * @return 요청한 날이 현재 날짜가  작다면...이전월01 (ex: 20091001) 형식으로 그렇지 않다면 현재월 + 01(ex:20091001) 형태로 반환
     */
    public static String getDateJudge(int dayInt){
        int judge = Integer.parseInt(CommUtil.getKST("dd"));
//			System.out.println("judge : " + judge);
//			System.out.println("judge > dayInt  :" +judge+" >: " +dayInt + ( judge > dayInt ));

        if ( judge > dayInt )
            return   CommUtil.getKST("yyyyMM")+"01" ;
        else{
            Calendar ge = GregorianCalendar.getInstance();
            ge.set(ge.get(Calendar.YEAR), ( ge.get(Calendar.MONTH) -1 ) , 1);
            //System.out.println("Calendar.MONTH : " + ge.get(Calendar.MONTH));
            int month= ge.get(Calendar.MONTH) +1;

            return  ge.get(Calendar.YEAR) +""+( (month >= 10)?month+"":"0"+month )  +"01" ;
        }
    }




    /**
     * 현재 날짜 기준으로 입력한 날짜보다 크다면 입력한 두달전을
     * @param dayInt 어느날짜를 기준으로 할건지
     * @return 요청한 날이 현재 날짜가  작다면...석달전월01 (ex: 20091001) 형식으로 그렇지 않다면 현재월 + 01(ex:20091001) 형태로 반환
     */
    public static String getDateJudge_2(int dayInt){
        int judge = Integer.parseInt(CommUtil.getKST("dd"));

        if ( judge > dayInt ){
            Calendar ge = GregorianCalendar.getInstance();
            ge.set(ge.get(Calendar.YEAR), ( ge.get(Calendar.MONTH) -2 ), 1);

            int month= ge.get(Calendar.MONTH) +1;
            return  ge.get(Calendar.YEAR) +""+( (month >= 10)?month+"":"0"+month )  +"01" ;
        }else{
            Calendar ge = GregorianCalendar.getInstance();
            ge.set(ge.get(Calendar.YEAR), ge.get(Calendar.MONTH), 1);
            int month= ge.get(Calendar.MONTH) + 1;

            return  ge.get(Calendar.YEAR) +""+( (month >= 10)?month+"":"0"+month )  +"01" ;
        }
    }





    /**
     * 현재(한국기준) 시간정보를 얻는다.                     	<BR>
     * informix전용의 DATETIME 관련자료형의 표기법을 따른다. 	<BR>
     * 표기법은 yyyy-mm-dd hh:mm:ss 							<BR>
     * @param    N/A														<BR>
     * @return   String 		yyyy-mm-dd hh:mm:ss형태의 현재 한국시간.    <BR>
     */
    public static String getKSTDateTime()
    {
        // Format the current time.

/*			SimpleDateFormat formatter  = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
			Date currentTime_1          = new Date();

			return formatter.format(currentTime_1);
*/
        return getDateFormat("yyyy/MM/dd HH:mm:ss");
    }//end method


    public static String getDateFormat(String formatType){
        SimpleDateFormat formatter  = new SimpleDateFormat (formatType);
        Date currentTime_1          = new Date();

        return formatter.format(currentTime_1);
    }


    private static String getDateFormat(String data, String formatType){
        SimpleDateFormat formatter  = new SimpleDateFormat (formatType);
        return formatter.format(data);
    }


    /**
     * 현재(한국기준) 시간정보를 얻는다.                     <BR>
     *      String CurWeek = CmicUtil.getKST("EEE");         <BR>
     * @param    format      얻고자하는 현재효일
     * @return   str 		현재 한국 시간.
     */
    public static String getKSTWeek(String format)
    {
        //1hour(ms) = 60s * 60m * 1000ms
        int millisPerHour = 60 * 60 * 1000;
        SimpleDateFormat fmt    = new SimpleDateFormat(format);
        SimpleTimeZone timeZone = new SimpleTimeZone(9*millisPerHour,"KST");
        fmt.setTimeZone(timeZone);

        long time   = System.currentTimeMillis();
        String str  = fmt.format(new java.util.Date(time));

        return str;
    }
    /**
     * java.sql.Date object를 얻기 위한 것     <BR>
     *
     * 데이블 구성 :	timetable (a DATE, b DATETIME YEAR TO SECOND)
     *
     * (예)
     *	   PreparedStatement	pstmt = conn.prepareStatement("Insert into timetable values (?, ? )" );
     *	   pstmt.setDate( 1, CSaUtil.getDate("1999-10-10");
     *
     * @param    dateString  		날짜문자열(yyyy-mm-dd)
     * @return   java.sql.Date
     */
    public static java.sql.Date getDate(String in_time)
    {
        return java.sql.Date.valueOf(in_time);
    }//end

    /**
     * 데이타베이스로 문자열을 저장하기 전에 변환
     * (주의) 한글변환을 위하여 사용
     * @param	str			DB로 저장할 한글이 들어있는 문자열
     * @return	tmpStr		8859_1 codeset으로 변환된 문자열
     */
    public static String toDB(String str)
    {
        String tmpStr = new String();

        try
        {
            return Ksc2Uni(str);
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace(System.err);
        }

        return tmpStr;
    }


    /**
     * 데이타베이스로부터 얻은 문자열을 변환
     * (주의) 한글변환을 위하여 사용함
     * @param	str			DB에서 가져온 한글이 들어있는 문자열
     * @return	tmpStr		KSC5601 codeset으로 변환된 문자열
     */
    public static String fromDB(String str)
    {
        String tmpStr = new String();

        try
        {
            tmpStr = Uni2Ksc(str);
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace(System.err);
        }

        return tmpStr;
    }


    /**
     * Ksc5601 ---> 8859_1 문자열로 변환
     * 예) Strint str = CtosUtil.Ksc2Uni("제목:\n");	<BR>
     * @param	KscStr
     * @return	String
     */
    public static String Ksc2Uni(String KscStr) throws java.io.UnsupportedEncodingException
    {
        if(KscStr == null)
        {
            return null;
        }
        else
        {
            return new String (KscStr.getBytes("KSC5601"), "8859_1");
        }
    }


    /**
     * 8859_1 ---> Ksc5601 문자열로 변환
     *
     * @param	UnicodeStr
     * @return	String
     */
    public static String Uni2Ksc(String UnicodeStr) throws UnsupportedEncodingException
    {
        if(UnicodeStr == null)
        {
            return null;
        }
        else
        {
            return new String (UnicodeStr.getBytes("8859_1"), "KSC5601");
        }
    }//end Uni2Ksc method


    /**
     * String을 int값으로 변환한다.           <BR>
     * int cnt = CmicUtil.stoi("10");	<BR>
     * @param    str     int값으로 변환될 String문자열.
     * @return   변환된 int 값.
     */
    public static int stoi(String str)
    {
        if(str == null ) return 0;
        if(str.trim().equals("")) return 0;

        return (Integer.valueOf(str).intValue());
    }

    /**
     * int값을 String으로 변환한다.           <BR>
     * 예) Strint str = CmicUtil.itos(10);	<BR>
     * @param    i   String으로 변환될 int 값.
     * @return   변환된 String 값.
     */
    public static String itos(int i)
    {
        return (new Integer(i).toString());
    }

    /**
     * deleteStr                                                   <BR>
     * 전체 문자열에서 사용자가 원하는 문자열을 제거한다.<BR>
     * @param    str           String 전체 문자열
     * @param    chr           String 삭제할 문자열
     * @return   Str           String 문자가 삭제된 문자열
     *
     */
    public static String deleteStr( String str, String chr )
    {
        String rtn = "";
        StringTokenizer st = new StringTokenizer( str, chr );

        while (st.hasMoreTokens())
        {
            rtn += st.nextToken();
        }

        return rtn;
    }


    /**
     * fullCharToString
     * 문자열앞에 입력된 문자로 총문자열 길이만큼 채운후 문자열을 return한다.
     * @param    strValue      String 문자열
     *           ch            char   채울문자
     *           totalLen      int    문자를 채운 총문자열길이
     * ex) "02", '0', 4  ----> "0002"
     *     "02", ' ', 4  ----> "  02"
     * @return   Str           String 문자열 출력
     */
    public static String fullCharToString(String strValue, char ch, int totalLen)
    {
        char[] data = {ch};
        String str  = new String(data);

        while (strValue.length() < totalLen)
        {
            strValue = str + strValue;
        }

        return strValue;

    }//fullCharToString(String strValue, char ch, int totalLen)


    /**
     * getLastDate                                        <BR>
     * 년과 월을 입력받아 해당 년,월,마지막 일을 return 한다.
     * @param    year          int 	 해당 년도
     * @param    month         int    해당 월
     * @return   Str           String 해당년,월,일 ex) "2000/02/29"
     *
     */
    public static String getLastDate(int year, int month)
    {
        //입력조건 검사
        if ( (year < 1900) || (year > 9999) )
        {
            return("-2");	//input year error
        }

        if ( (month <= 0) || (month > 12) )
        {
            return("-3");	//input month error
        }

        int currentMonth = month - 1;
        boolean isLeapYear = false;		//윤년유무
        String str = null;			//return value


        int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int maxDate = daysInMonth[currentMonth];

        /**윤년 계산 **/
        if((year % 400) == 0) isLeapYear = true;
        else if((year > 1582) && ((year % 100) == 0)) isLeapYear = false;
        else if((year % 4) == 0) isLeapYear = true;
        else isLeapYear = false;

        //2월이며 윤년일때 2월은 29일까지.
        if(currentMonth == 1 && isLeapYear) maxDate += 1;

        if(month >=1 && month <= 9)
            str = year + "/0" + month + "/" + maxDate;
        else
            str = year + "/" + month + "/" + maxDate;

        return(str);
    }


    /**
     * getLastDate                                       <BR>
     * 년과 월을 입력받아 해당 년,월,마지막 일을 return 한다.
     * @param    date		  String 년월일(8자리)  ex) "20000201"
     * @return   Str           String 해당년,월,일 ex) "2000/02/29"
     *
     */
    public static String getLastDate(String date)
    {

        //입력조건 검사
        if (date.length() != 8)
            return ("-1");		//input length error

        int year = 	Integer.valueOf(date.substring(0,4)).intValue();
        int month = Integer.valueOf(date.substring(4,6)).intValue();



        //입력조건 검사
        if ( (year < 1900) || (year > 9999) )
        {

            return("-2");	//input year error
        }

        if ( (month <= 0) || (month > 12) )
        {
            return("-3");	//input month error
        }

        int currentMonth = month - 1;
        boolean isLeapYear = false;		//윤년유무
        String str = null;				//return value


        int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int maxDate = daysInMonth[currentMonth];

        /**윤년 계산 **/
        if((year % 400) == 0) isLeapYear = true;
        else if((year > 1582) && ((year % 100) == 0)) isLeapYear = false;
        else if((year % 4) == 0) isLeapYear = true;
        else isLeapYear = false;

        //2월이며 윤년일때 2월은 29일까지.
        if(currentMonth == 1 && isLeapYear) maxDate += 1;

        if(month >=1 && month <= 9)
            str = year + "/0" + month + "/" + maxDate;
        else
            str = year + "/" + month + "/" + maxDate;

        return(str);

    }


    /**
     * getLastDate                                       <BR>
     * 년과 월을 입력받아 해당 년,월,두달전의 마지막 일을 return 한다.
     * @param    date		  String 년월일(8자리)  ex) "20000201"
     * @param    mon		  int 월(1자리)  ex) "1"
     * @return   Str           String 해당년,월,일 ex) "2000/02/29"
     *
     */
    public static String getLastDate_n(String date,int mon)
    {

        //입력조건 검사
        if (date.length() != 8)
            return ("-1");		//input length error

        int year = 	Integer.valueOf(date.substring(0,4)).intValue();
        int month = Integer.valueOf(date.substring(4,6)).intValue() - mon;



        //입력조건 검사
        if ( (year < 1900) || (year > 9999) )
        {

            return("-2");	//input year error
        }

        if ( (month <= 0) || (month > 12) )
        {
            return("-3");	//input month error
        }

        int currentMonth = month - 1;
        boolean isLeapYear = false;		//윤년유무
        String str = null;				//return value


        int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int maxDate = daysInMonth[currentMonth];

        /**윤년 계산 **/
        if((year % 400) == 0) isLeapYear = true;
        else if((year > 1582) && ((year % 100) == 0)) isLeapYear = false;
        else if((year % 4) == 0) isLeapYear = true;
        else isLeapYear = false;

        //2월이며 윤년일때 2월은 29일까지.
        if(currentMonth == 1 && isLeapYear) maxDate += 1;

        if(month >=1 && month <= 9)
            str = year + "/0" + month + "/" + maxDate;
        else
            str = year + "/" + month + "/" + maxDate;

        return(str);

    }



    //************************************************************************
    // NullChange
    // @return 	String	null값을 ""로 변환해서 리턴한다.
    //	2001.12.19 	OMT 추가
    //************************************************************************
    public static String NullChange( String val)
    {
        if( val == null )
            val = "";
        return val;
    }

    /**
     * NullChange
     * @param str 검사할 : 값
     * @param NVLString : 대체 값
     * @return
     */
    public static String NullChange(String str, String NVLString) {
        if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")) || (str.trim().equals("undefined")))
            return NVLString;
        else
            return str;
    }

    //	오리지널 문자열을 Hex로 인코딩하는 함수
    public static String StringToHex4(String TempString)
    {
        StringBuffer ReturnString = new StringBuffer();
        char[] charStr = TempString.toCharArray();

        for (int i = 0; i < charStr.length; i++)
        {
            String tmp = "";
            tmp = "0000" + Integer.toHexString((int) charStr[i]);

            String tmp2 = tmp.substring(tmp.length() - 4);

            ReturnString.append(tmp2);

        }

        return ReturnString.toString().toUpperCase();

    }


    //	Hex 문자열을 오리지널 문자열로 복원하는 함수
    public static String Hex4ToStr(String encode_data)
    {
        StringBuffer newData = new StringBuffer();

        int iNo = 0;
        for (int i=0;i<encode_data.length()/4;i++)
        {
            String str = encode_data.substring(iNo,iNo+4);
            int iNum = Integer.parseInt(str, 16);
            //int iNum = Integer.valueOf(str, 16);

            char strChar = (char)iNum;

            newData.append(strChar);

            iNo = iNo + 4;
        }
        return newData.toString();
    }

    //************************************************************************
    // SpaceChange	Tuxedo와의 연동을 위해 " "으로 리턴
    // @return 	String	null값을 ""로 변환해서 리턴한다.
    //	2005.05.09 	KSH 추가
    //************************************************************************
    public static String SpaceChange( String val)
    {
        if( val == null )
            val = " ";
        if( val != null && val.equals("")) {
            val = " ";
        }
        return val;
    }

    //************************************************************************************
    // GetString	StringTokenizer를 이용해 입력받는 인덱스에 해당하는 데이터를 추출한다.
    // @return 	String	null값을 ""로 변환해서 리턴한다.
    //	2005.05.13 	KSH 추가
    //*************************************************************************************
    public static String GetString(String vSting, int vIndex)
    {
        StringTokenizer  vST = new StringTokenizer(vSting, "|");
        String fString	= "";
        int count		= 0;
        while(vST.hasMoreTokens())
        {
            fString = vST.nextToken();
            count++;

            if( count == vIndex ) {
                break;
            }

        }

        return fString;
    }



    //************************************************************************
    // isNotNull
    // @return 	null이면 false
    //	2002-08-08 	추가
    //************************************************************************
    public static boolean isNotNull(String var)
    {
        boolean bChk = true;
        int nLength = 0;

        if (var == null)
        {
            bChk = false;
        }
        else
        {
            nLength = var.length();

            if (nLength > 0)
            {
                var = var.trim();

                if (var.equals("null") || var.equals(""))
                {
                    bChk = false;
                }
            }
            else
                bChk = false;
        }

        return bChk;
    }

    //************************************************************************
    //	Date 스트링에 mask를 씌운다.
    //	년월일의 mask는 strMask로 처리하며, 시분초의 mask는 ":"로 한다.
    //	2002-08-08 	추가
    //************************************************************************
    public static String maskDate(String strDate, String strMask)
    {
        if (isNotNull(strDate))
        {
            StringBuffer strResult = new StringBuffer()
                    .append(strDate.substring(0,4))
                    .append(strMask)
                    .append(strDate.substring(4,6))
                    .append(strMask)
                    .append(strDate.substring(6,8));


            if (strDate.length() == 12)	// YYYYMMDDHHMM
            {
                strResult
                        .append(" ")
                        .append(strDate.substring(8,10))
                        .append(":")
                        .append(strDate.substring(10));
            }
            else
            if (strDate.length() == 14)	// YYYYMMDDHHMMSS
            {
                strResult
                        .append(" ")
                        .append(strDate.substring(8,10))
                        .append(":")
                        .append(strDate.substring(10,12))
                        .append(":")
                        .append(strDate.substring(12));
            }
            return strResult.toString();
        }
        else
        {
            return "";
        }

    }

    //************************************************************************
    //	Date 스트링에 "."으로, 시분초의 mask는 ":"mask를 씌운다.
    //	2002-08-08 	추가
    //************************************************************************
    public static String maskDate(String strDate)
    {
        if (isNotNull(strDate))
        {
            StringBuffer strResult = new StringBuffer()
                    .append(strDate.substring(0,4))
                    .append(".")
                    .append(strDate.substring(4,6))
                    .append(".")
                    .append(strDate.substring(6,8));

            if (strDate.length() == 12)	// YYYYMMDDHHMM
            {
                strResult
                        .append(" ")
                        .append(strDate.substring(8,10))
                        .append(":")
                        .append(strDate.substring(10));
            }
            else
            if (strDate.length() == 14)	// YYYYMMDDHHMMSS
            {
                strResult
                        .append(" ")
                        .append(strDate.substring(8,10))
                        .append(":")
                        .append(strDate.substring(10,12))
                        .append(":")
                        .append(strDate.substring(12));
            }
            return strResult.toString();
        }
        else
        {
            return "";
        }

    }

    //************************************************************************
    //	날짜와 날짜 사이의 시분초 단위로 간격차를 구한다.(예: 이장시간)
    //  return: 시:분:초
    //	2002-08-13 	추가
    //************************************************************************
    public static String daysBetween(String from, String to) throws java.text.ParseException
    {
        String format    = "yyyyMMddHHmmss";
        String strHour   = "";
        String strMinute = "";
        String strSecond = "";

        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
        java.util.Date d1 = formatter.parse(from);
        java.util.Date d2 = formatter.parse(to);

        long duration = d2.getTime() - d1.getTime();

        int nHour   = (int)(duration / (1000 * 60 * 60));
        int nMinute = (int)((duration / (1000 * 60)) % 60);
        int nSecond = (int)((duration / 1000) % 60);

        if (nHour   > 9) strHour   = String.valueOf(nHour);
        else             strHour   = "0".concat(String.valueOf(nHour));
        if (nMinute > 9) strMinute = String.valueOf(nMinute);
        else             strMinute = "0".concat(String.valueOf(nMinute));
        if (nSecond > 9) strSecond = String.valueOf(nSecond);
        else             strSecond = "0".concat(String.valueOf(nSecond));

        StringBuffer sb = new StringBuffer()
                .append(strHour)
                .append(":")
                .append(strMinute)
                .append(":")
                .append(strSecond);

        return sb.toString();
    }

    //************************************************************************
    //	날짜와 날짜 사이의 분초 단위로 간격차를 구한다.(예: 이장시간)
    //  return: 분:초
    //	2002-08-13 	추가
    //************************************************************************
    public static String daysBetweenMinute(String from, String to) throws java.text.ParseException
    {
        String format    = "yyyyMMddHHmmss";
        String strMinute = "";
        String strSecond = "";

        SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.KOREA);


        Date d1 = new Date();
        Date d2 = new Date();

        if (from.length() == 4) from = from + "00";
        if (to.length() == 4) to = to + "00";

        d1 = formatter.parse(from);
        d2 = formatter.parse(to);

        long duration = d2.getTime() - d1.getTime();

        int nMinute = (int)(duration / (1000 * 60));
        int nSecond = (int)((duration / 1000) % 60);

        if (nMinute > 9) strMinute = String.valueOf(nMinute);
        else             strMinute = "0".concat(String.valueOf(nMinute));
        if (nSecond > 9) strSecond = String.valueOf(nSecond);
        else             strSecond = "0".concat(String.valueOf(nSecond));

        StringBuffer sb = new StringBuffer()
                .append(strMinute)
                .append(":")
                .append(strSecond);

        return sb.toString();
    }

    //************************************************************************
    //	날짜와 날짜 사이의 초 단위로 간격차를 구한다.
    //  return: 초
    //	2005-05-04 	추가
    //************************************************************************
    public static String daysBetweenSecond(String from, String to) throws java.text.ParseException
    {
        String format    = "yyyyMMddHHmmss";
        String strSecond = "";

        SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.KOREA);


        Date d1 = new Date();
        Date d2 = new Date();

        if (from.length() == 4) from = from + "00";
        if (to.length() == 4) to = to + "00";

        d1 = formatter.parse(from);
        d2 = formatter.parse(to);

        long duration = (d2.getTime() - d1.getTime()) / 1000;

        strSecond = String.valueOf(duration);


        return strSecond;
    }


    //************************************************************************
    //	오늘의 [년월일 요일]을 구해서 리턴한다.
    //  return: 9999년 99월 99일 W요일
    //	2002-08-22 	추가
    //************************************************************************
    public static String getDayOfWeek()
    {
        Calendar cal= Calendar.getInstance();

        StringBuffer sb = new StringBuffer()
                .append(String.valueOf(cal.get(Calendar.YEAR)))
                .append("년 ")
                .append(String.valueOf((cal.get(Calendar.MONTH)+1)))
                .append("월 ")
                .append(String.valueOf(cal.get(Calendar.DATE)))
                .append("일 ");

        switch(cal.get(Calendar.DAY_OF_WEEK))
        {
            case 1:sb.append("일요일"); break;
            case 2:sb.append("월요일"); break;
            case 3:sb.append("화요일"); break;
            case 4:sb.append("수요일"); break;
            case 5:sb.append("목요일"); break;
            case 6:sb.append("금요일"); break;
            case 7:sb.append("토요일"); break;
            default:
        }
        return sb.toString();
    }

    //************************************************************************
    //	오늘을 기준으로 일정기간 이전, 이후의 날짜를 구한다.
    //  return: YYYYMMDD
    //	2002-08-23 	추가
    //************************************************************************
    public static String calcDays(int calc)
    {
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        cal.add(Calendar.DATE, calc);

        return sdf.format(cal.getTime());
    }

    //************************************************************************
    //	주어진 날짜를 기준으로 일정기간 이전, 이후의 날짜를 구한다.
    //  return: YYYYMMDD
    //	2002-08-23 	추가
    //************************************************************************
    public static String calcDays(String y, int calc)
    {
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        cal.set(Integer.parseInt(y.substring(0,4)),
                Integer.parseInt(y.substring(4,6))-1,
                Integer.parseInt(y.substring(6)));
        cal.add(Calendar.DATE, calc);

        return sdf.format(cal.getTime());
    }

    //************************************************************************
    //	주어진 날짜를 기준으로 일정기간 이전, 이후의 날짜를 구한다.
    //  return: YYYYMMDD
    //	2002-08-23 	추가
    //************************************************************************
    public static String calcMonth(String y, int calc)
    {
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        cal.set(Integer.parseInt(y.substring(0,4)),
                Integer.parseInt(y.substring(4,6))-calc-1,
                Integer.parseInt(y.substring(6)));
        cal.add(Calendar.DATE, calc);

        return sdf.format(cal.getTime());
    }


    //************************************************************************
    //	Time 스트링에 ":"으로 mask를 씌운다.
    //	hh:mm 또는 hh:mm:ss 	추가
    //************************************************************************
    public static String maskTime(String strDate)
    {
        if (isNotNull(strDate))
        {
            StringBuffer strResult = new StringBuffer()
                    .append(strDate.substring(0,2))
                    .append(":")
                    .append(strDate.substring(2,4));

            if (strDate.length() > 4)
            {
                strResult.append(":")
                        .append(strDate.substring(4));
            }

            return strResult.toString();
        }
        else
        {
            return "";
        }
    }

    //************************************************************************
    //	숫자에 콤마넣어서 반환한다.
    //  return: 999,999,999
    //	2002-08-27 	추가
    //************************************************************************
    public static String Comma_Conv(String dt)
    {
        if ( dt == null || dt == "" )
            return "0";

        int i = 0;
        int mok = 0;
        int div = 0;

        int size = dt.length();
        String str = "";

        div = size / 3;
        mok = size % 3;
        str = dt.substring( 0, mok );

        for (i = 0 ; i < div; i++ )
        {
            if ( i == 0 && mok == 0 )
                str = dt.substring( (i * 3) + mok, (i * 3) + mok + 3 );
            else
                str = str + "," + dt.substring( (i * 3) + mok, (i * 3) + mok + 3 );
        }

        return str.replaceFirst("-,", "-");
    }


    public String fmVal( float f)
    {
        java.text.DecimalFormat df = new DecimalFormat("###0.00");

        return df.format(f);
    }


    /**
     * 현재시간을 기준으로 일정시간(분)  이전, 이후의 날짜를 구한다.
     * @param    format      얻고자하는 현재시간의 Type
     *           min         얻고자하는 몇분 전(-) 후(+)의 값
     * @return   str 		현재 한국 시간.
     */
    public static String getDate2(String format, int min)
    {
        //1hour(ms) = 60s * 60m * 1000ms
        int millisPerHour = 60 * 60 * 1000;
        int millisMinute  = min * 60 * 1000;

        SimpleDateFormat fmt    = new SimpleDateFormat(format);
        SimpleTimeZone timeZone = new SimpleTimeZone(9*millisPerHour,"KST");

        fmt.setTimeZone(timeZone);

        long time = System.currentTimeMillis() + millisMinute;

        String str = fmt.format(new java.util.Date(time));

        return str;
    }

    public static int daysBetweenDate(String from, String to) throws java.text.ParseException
    {
        String format    = "yyyyMMdd";
        SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.KOREA);

        Date d1 = new Date();
        Date d2 = new Date();

        d1 = formatter.parse(from);
        d2 = formatter.parse(to);

        long duration = d2.getTime() - d1.getTime();

        int nDate = (int)(duration / (1000 * 60 * 60 * 24));

        return nDate;
    }

    public static float processState(String div, String val)
    {
        int nLen = val.length();
        float fResult = 0;
        char[] chVal = val.toCharArray();
        char chDiv = 0;


        if (div.equals("VSZ")) chDiv = 'G';
        else
        if (div.equals("RSS")) chDiv = 'M';

        if (chVal[nLen-1] == chDiv)
        {
            val = val.substring(0, nLen-1);
            fResult = Float.parseFloat(val);
        }

        return fResult;
    }

    /**
     * <PRE>
     * betweenMonth()   // from, to 년월 사이의 개월수를 구한다
     *
     * </PRE>
     * @param
     *          from                    from 년월 - yyyymm
     *          to                      to   년월 - yyyymm
     *
     * @return  int
     *          duration				from, to 년월 사이의 개월수
     */
	/*
	public static int betweenMonth(String from, String to) throws java.text.ParseException
    {
    	String format    = "yyyyMM";
        SimpleDateFormat formatter  = new SimpleDateFormat(format, java.util.Locale.KOREA);
        Date d1 = formatter.parse(from, new ParsePosition(0));
		Date d2 = formatter.parse(to  , new ParsePosition(0));

        d1 = formatter.parse(from);
        d2 = formatter.parse(to);

        long duration = d2.getMonth() - d1.getMonth();
		long year     = d2.getYear() - d1.getYear();

		duration = (year* 12) + duration;

		return (int)duration;
    }
    */
    /**
     * <PRE>
     * nextMonth()   // 해당월을 기준으로 일정개월 이후의 월을 구한다
     *
     * </PRE>
     * @param
     *          from                    from 년월 - yyyymm
     *          to                      to   년월 - yyyymm
     *
     * @return  int
     *          duration				from, to 년월 사이의 개월수
     */

    public static String nextMonth(String from, int next) throws java.text.ParseException
    {
        String format    = "yyyyMM";
        SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.KOREA);
        Calendar cal     = new GregorianCalendar();

        Date d1 = formatter.parse(from, new ParsePosition(0));

        cal.setTime(d1);
        cal.add(Calendar.DATE, (31*next));  //* 이부분 수정 요망.. 31 --> *//

        return formatter.format(cal.getTime());
    }


    /**
     * <PRE>
     * 왼쪽을 기준으로 임의의 문자열 추가
     * </PRE>
     * @param   String src	출력하고자 하는 문자열
     *			int n		출력하고자 하는 문자열의 총길이
     *			String pad	빈공백 대신에 채워넣을 문자열
     *
     * @return  String		빈공백이 채워진 문자열
     */
    public static String lpad(String src, int n, String pad)
    {
        int size = Integer.MAX_VALUE;

        if( src != null && pad != null)
        {
            size = src.length();
        }

        if( 0 < size && size < n )
        {
            StringBuffer sb = new StringBuffer();
            for( int i = 0; i < n - size; i++)
            {
                sb.append(pad);
            }
            sb.append(src);
            return sb.toString();
        }
        return src;
    }

    public static String changeTelNumberSplit(String s, int i)
    {
        if(s.equals("") || s == null || s.length() != 12)
            return "";
        switch(i)
        {
            case 1: // '\001'
                String s2 = s.substring(0, 4);
                if(s2.charAt(0) == '0')
                {
                    String s4 = "";
                    s4 = s2.substring(1, 4);
                    if(s4.charAt(0) != '0')
                        return s2;
                    String s5 = "";
                    s5 = s2.substring(2, 4);
                    if(s5.charAt(0) != '0')
                        return s4;
                    String s6 = "";
                    s6 = s2.substring(3, 4);
                    if(s6.charAt(0) != '0')
                        return s5;
                } else
                {
                    return s2;
                }
                // fall through

            case 2: // '\002'
                String s3 = s.substring(4, 8);
                if(s3.charAt(0) == '0')
                    return s.substring(5, 8);
                else
                    return s3;

            case 3: // '\003'
                return s.substring(8, 12);

            default:
                return "";
        }
    }


    public static String formatDateStr(String s, String s1)
    {
        String s2 = "";
        //System.out.println("************** Str1 ******* " + s);
        if(s == null || s.trim().equals(""))
            return s2;
        if(s1.equals("/"))
            s2 = s.substring(0, 4) + "/" + s.substring(4, 6) + "/" + s.substring(6, 8);
        else
            s2 = s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 8);
        return s2;
    }


    public static String cutsubString(String s, int i, int j)
    {
        if(s == null)
            return "";
        if(s.length() < i)
            return s;
        if(s.length() < j)
            return s;
        if(j == 0)
        {
            s = s.substring(i);
            return s;
        }
        if(s.length() > j)
            s = s.substring(i, j);
        return s;
    }

    public static String cutsubJumin(String s)
    {
        if(s == null)
            return "";
        if(s.length() == 0)
        {
            return "";
        } else
        {
            s = s.substring(0, 6) + "-" + s.substring(6);
            return s;
        }
    }


    //-----------------------문자를 ''로 실질적처리---------------------//
    public static String replace(String a,String b,String str)
    {
        String msg = null;
        try
        {
            StringTokenizer sub1 = new StringTokenizer(str,a,false);
            int i = 1;
            while (sub1.hasMoreElements())
            {
                if (i == 1)//처음분리한 문자열
                {
                    msg = sub1.nextToken();
                }
                else
                {
                    msg = msg + b + sub1.nextToken();
                }

                i = 2;//2번째 분리한 문자열
            }
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
        }
        return msg;
    }

    //문자열 치환함수
	 /*
	 public static String replace_str(String exp_str,String find_str, String repl_str)
	 {
		StringBuffer msg =  new StringBuffer();

		try
		{
			String[] result = exp_str.split(find_str);

		     for (int x=0; x<result.length; x++)
		     {
		         if(x==0)
		         {
		        	 msg.append(result[x]);
		         }
		         else
		         {
		        	 msg.append(repl_str + result[x]);
		         }
		     }
 		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		return msg.toString();
     }
     */

    /**
     * changeTelNumberCombi                                        <BR>
     * 국번, 지역번호, 번호를 입력받아 12자리 문자열 전화번호로 return한다.
     * @param    number1       String 국번
     *           number2       Stirng 지역번호
     *           number3       String 번호
     * ex) "053", "542", "7845"  ----> "005305427845"
     * @return   Str           String 전화번호 출력
     */
    public static String changeTelNumberCombi(String number1, String number2, String number3)
    {
        if(number1.equals("") && number2.equals("") && number3.equals("") )
            return "";
        if(number1 == null && number2 == null && number3 == null)
            return "";

        String telNumber = "";

        telNumber  = fullCharToString(number1,'0', 4);
        telNumber += fullCharToString(number2,'0', 4);
        telNumber += fullCharToString(number3,'0', 4);

        return telNumber;
    }//changeTelNumberCombi(String number1, String number2, String number3)

    /**
     * 현재 날짜+시간 Full( 오라클의 YYYYMMDDHH24MISS)
     * @return  String 20091130144357 이렇게 Return
     */
    public static String getDate()
    {
        Calendar now = Calendar.getInstance();

        int yy = now.get(Calendar.YEAR);
        String strYy = "" + yy;

        int mm = now.get(Calendar.MONTH) + 1;
        String strMm = "" + mm;
        if (mm < 10)
            strMm = "0" + mm;

        int dd = now.get(Calendar.DAY_OF_MONTH);
        String strDd = "" + dd;
        if (dd < 10)
            strDd = "0" + dd;

        int hh = now.get(Calendar.HOUR_OF_DAY);
        String strHh = "" + hh;
        if (hh < 10)
            strHh = "0" + hh;

        int min = now.get(Calendar.MINUTE);
        String strMin = "" + min;
        if (min < 10)
            strMin = "0" + min;

        int ss = now.get(Calendar.SECOND);
        String strSs = "" + ss;
        if (ss < 10)
            strSs = "0" + ss;

        String time = strYy + strMm + strDd + strHh + strMin + strSs;

        return time;
    }



    /**  <PRE>
     *  년을 select에 보여주고, 당해년도 보이기
     *  </PRE>
     *
     * @param  String , String
     *
     * @return String
     */
    public static String YearList(String select_name, String select_value)
    {
        StringBuffer sb = new StringBuffer();
        String vYear = CommUtil.getDate().substring(0,4);
        String tmpYear = null;

        sb.append("<select name=" + select_name + " class='tm_box' > \n");
        for(int i=1999; i<2015; i++) {
            tmpYear = Integer.toString(i);
            sb.append("<option value='" + tmpYear + "'");
            if(select_value == null || select_value.equals("")) {
                if(tmpYear.equals(vYear)) {
                    sb.append(" selected");
                }
            } else {
                if(tmpYear.equals(select_value)) {
                    sb.append(" selected");
                }
            }
            sb.append("> " + tmpYear + "</option>\n");
        }
        sb.append("</select>");
        return sb.toString();
    }


    /**  <PRE>
     *  년을 select에 보여주고, 당해년도 보이기
     *  </PRE>
     *
     * @param  String , String
     *
     * @return String
     */
    public static String YearListHist(String select_name, String select_value)
    {
        StringBuffer sb = new StringBuffer();
        String vYear = CommUtil.getDate().substring(0,4);
        String tmpYear = null;

        sb.append("<select name=" + select_name + " class='tm_box' onChange='setYear()'> \n");
        for(int i=1999; i<2015; i++) {
            tmpYear = Integer.toString(i);
            sb.append("<option value='" + tmpYear + "'");
            if(select_value == null || select_value.equals("")) {
                if(tmpYear.equals(vYear)) {
                    sb.append(" selected");
                }
            } else {
                if(tmpYear.equals(select_value)) {
                    sb.append(" selected");
                }
            }
            sb.append("> " + tmpYear + "</option>\n");
        }
        sb.append("</select>");
        return sb.toString();
    }



    /**  <PRE>
     *  년을 select에 보여주고, 당해년도 보이기
     *  </PRE>
     *
     * @param  String , String
     *
     * @return String
     */
    public static String YearList(String select_name, String select_value,String option)
    {
        StringBuffer sb = new StringBuffer();
        String vYear = CommUtil.getDate().substring(0,4);
        String tmpYear = null;

        sb.append("<select name=" + select_name + " "+option+">\n");
        for(int i=1999; i<2015; i++) {
            tmpYear = Integer.toString(i);
            sb.append("<option value='" + tmpYear + "'");
            if(select_value == null || select_value.equals("")) {
                if(tmpYear.equals(vYear)) {
                    sb.append(" selected");
                }
            } else {
                if(tmpYear.equals(select_value)) {
                    sb.append(" selected");
                }
            }
            sb.append("> " + tmpYear + "</option>\n");
        }
        sb.append("</select>");
        return sb.toString();
    }


    /**  <PRE>
     *  월을 select에 보여주고, 당해년도 보이기
     *  </PRE>
     *
     * @param  String , String
     *
     * @return String
     */
    public static String MonthListHist(String select_name, String select_value)
    {
        StringBuffer sb = new StringBuffer();

        //System.out.println("ComMyfunc.getDate() -: " + ComMyfunc.getDate());

        String vMonth = CommUtil.getDate().substring(4,6);
        String tmpMonth = null;

        //System.out.println("vMonth -----: " + vMonth);

        sb.append("<select name=" + select_name + " class='tm_box' onChange='setYear()'>\n");
        for(int i=1; i<=12; i++) {
            if(i>=10) tmpMonth = Integer.toString(i);
            else tmpMonth = "0" + Integer.toString(i);

            sb.append("<option value='" + tmpMonth + "'");
            if(select_value == null || select_value.equals("")) {
                if(tmpMonth.equals(vMonth)) {
                    sb.append(" selected");
                }
            } else {
                if(tmpMonth.equals(select_value)) {
                    sb.append(" selected");
                }
            }
            sb.append("> " + tmpMonth + "</option>\n");
        }
        sb.append("</select>");
        return sb.toString();
    }



    /**  <PRE>
     *  월을 select에 보여주고, 당해년도 보이기
     *  </PRE>
     *
     * @param  String , String
     *
     * @return String
     */
    public static String MonthList(String select_name, String select_value)
    {
        StringBuffer sb = new StringBuffer();

        //System.out.println("ComMyfunc.getDate() -: " + ComMyfunc.getDate());

        String vMonth = CommUtil.getDate().substring(4,6);
        String tmpMonth = null;

        //System.out.println("vMonth -----: " + vMonth);

        sb.append("<select name=" + select_name + " class='tm_box'>\n");
        for(int i=1; i<=12; i++) {
            if(i>=10) tmpMonth = Integer.toString(i);
            else tmpMonth = "0" + Integer.toString(i);

            sb.append("<option value='" + tmpMonth + "'");
            if(select_value == null || select_value.equals("")) {
                if(tmpMonth.equals(vMonth)) {
                    sb.append(" selected");
                }
            } else {
                if(tmpMonth.equals(select_value)) {
                    sb.append(" selected");
                }
            }
            sb.append("> " + tmpMonth + "</option>\n");
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**  <PRE>
     *  월을 select에 보여주고, 당해년도 보이기
     *  </PRE>
     *
     * @param  String 이름, String 선택값 ,String 옵션
     *
     * @return String
     */
    public static String MonthList(String select_name, String select_value,String option)
    {
        StringBuffer sb = new StringBuffer();

        //System.out.println("ComMyfunc.getDate() -: " + ComMyfunc.getDate());

        String vMonth = CommUtil.getDate().substring(4,6);
        String tmpMonth = null;

        //System.out.println("vMonth -----: " + vMonth);

        sb.append("<select name=" + select_name + " "+option+">\n");
        for(int i=1; i<=12; i++) {
            if(i>=10) tmpMonth = Integer.toString(i);
            else tmpMonth = "0" + Integer.toString(i);

            sb.append("<option value='" + tmpMonth + "'");
            if(select_value == null || select_value.equals("")) {
                if(tmpMonth.equals(vMonth)) {
                    sb.append(" selected");
                }
            } else {
                if(tmpMonth.equals(select_value)) {
                    sb.append(" selected");
                }
            }
            sb.append("> " + tmpMonth + "</option>\n");
        }
        sb.append("</select>");
        return sb.toString();
    }


    // 2005-03-15  추가한 메소드
    /**
     *   int flag   1:달이동,2:일이동
     *  int offset :이동달(일)수
     **/
    public static String addDate(String date, int flag, int offset) {
        //입력받은 날짜를 기준으로 월,일 이동
        int yyyy = 0, mm = 0, dd = 1;
        int ryyyy = 0, rmm = 0, rdd = 0;

        try {
            StringBuffer buf = new StringBuffer();
            if(flag==1 && date.length() != 6)
                return "";
            if(flag==2 && date.length() != 8)
                return "";

            yyyy = Integer.parseInt(date.substring(0,4));
            mm = Integer.parseInt(date.substring(4,6))-1 ;

            if(flag != 1) 	    dd = Integer.parseInt(date.substring(6,8));

            java.util.Calendar endx_date = new GregorianCalendar(yyyy, mm, dd);
            //Date trialTime = new Date();
            //endx_date.setTime(trialTime);
            if (flag == 1) {
                endx_date.add(Calendar.MONTH, offset);
            } else {
                if (flag == 2) {
                    endx_date.add(Calendar.DATE, offset);
                }
            }

            ryyyy = endx_date.get(Calendar.YEAR);
            rmm   = endx_date.get(Calendar.MONTH) +1 ;
            rdd   = endx_date.get(Calendar.DATE);

            buf.append(ryyyy);

            if (rmm <= 9){
                buf.append('0');
            }

            buf.append(rmm);

            if(flag == 2){
                if (rdd <= 9)	buf.append('0');
                buf.append(rdd);
            }

            return buf.toString();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        }
    }

    /*-------------------------------------------------------------------
        Description   : null 치환
    -------------------------------------------------------------------*/
    public static long nvl(String value, long defaultValue)
    {
        return ( value == null ) ? defaultValue : Long.parseLong(value);
    }
    /*-------------------------------------------------------------------
        Description   : null 치환
    -------------------------------------------------------------------*/
    public static String nvl(String value, String defaultValue)
    {
        return ( value == null ) ? defaultValue : value;
    }

    // 반올림(round) 메소드
    public static double round(double infval,int step)
    {
        double outfval;
        outfval = (Math.round(infval*Math.pow(10,step))/(Math.pow(10,step)));

        return outfval;
    }

    /**  <PRE>문자열을 HEX타입으로 변환 </PRE>
     *
     * @param  String 변경전
     *
     * @return String 4비트문자
     */
    public static String StringTo4Hex(String TempString) throws Exception
    {
        StringBuffer ReturnString = new StringBuffer();
        char[] charStr = TempString.toCharArray();

        for (int i = 0; i < charStr.length; i++)
        {
            String tmp = "";
            tmp = "0000" + Integer.toHexString((int) charStr[i]);

            String tmp2 = tmp.substring(tmp.length() - 4);

            ReturnString.append(tmp2);

        }

        return ReturnString.toString().toUpperCase();

    }//END METHOD


    /**  <PRE>
     *  [TUXEDO] null문자 또는 ""인 문자를 " " blank문자로 변환한다.
     *           턱시도에 문자열을 전달하기 위해..
     *  </PRE>
     *
     * @param  String 변경전
     *
     * @return String 4비트문자
     *
     * @see
     *
     * @author  2005-05-10
     */
    public static String NullChange2( String val)
    {
        if( val == null || val.equals("") ) val = " ";

        return val;
    }


    /**
     * 현재날자에 +/-한 날짜의 요일을 구한다.
     * @param    amount	+/- 하고자하는 날짜/요일
     * @return   String 	YYYMMDD의 날짜/요일(1: 일요일, 2: 월요일, 3: 화요일, 4: 수요일, 5: 목요일, 6: 금요일, 7: 토요일)
     * @author   김선희  | 2005-06-24
     */
    public static String[] getCalendarDay(int amount)
    {
        String[]   aDay   = new String[2];
        Calendar rightNow = Calendar.getInstance();

        rightNow.add(Calendar.DATE, amount);

        int year = rightNow.get(Calendar.YEAR);
        int month= rightNow.get(Calendar.MONTH)+1;
        int date = rightNow.get(Calendar.DATE);
        int day  = rightNow.get(Calendar.DAY_OF_WEEK);

        String sYear    = String.valueOf(year);
        String sMonth   = String.valueOf(month);
        String sDate    = String.valueOf(date);
        String sDay     = String.valueOf(day);


        if( month < 10 ) {
            sMonth = "0"+sMonth;
        }

        if( date < 10 ) {
            sDate = "0"+sDate;
        }

        aDay[0] = sYear + sMonth + sDate;
        aDay[1] = sDay;

        return aDay;


    }//end method


    /**
     * 특정날자에 +/-한 날짜의 요일을 구한다.
     * @param    amount	+/- 하고자하는 날짜/요일
     * @return   String 	YYYYMMDD의 날짜/요일(1: 일요일, 2: 월요일, 3: 화요일, 4: 수요일, 5: 목요일, 6: 금요일, 7: 토요일)
     * @author   김선희  | 2005-06-24
     */
    public static String[] getCalendarDay(String specialDay, int amount)
    {
        String[]   aDay   = new String[2];
        Calendar rightNow = Calendar.getInstance();

        int specialyear = 0;
        int specialmonth= 0;
        int specialday  = 0;

        if( specialDay.length() == 8 ) {
            specialyear   = Integer.parseInt(specialDay.substring(0,4));
            specialmonth  = Integer.parseInt(specialDay.substring(4,6))-1;
            specialday    = Integer.parseInt(specialDay.substring(6));
        }

        rightNow.set(specialyear, specialmonth, specialday);

        rightNow.add(Calendar.DATE, amount);

        int year = rightNow.get(Calendar.YEAR);
        int month= rightNow.get(Calendar.MONTH)+1;
        int date = rightNow.get(Calendar.DATE);
        int day  = rightNow.get(Calendar.DAY_OF_WEEK);

        String sYear    = String.valueOf(year);
        String sMonth   = String.valueOf(month);
        String sDate    = String.valueOf(date);
        String sDay     = String.valueOf(day);


        if( month < 10 ) {
            sMonth = "0"+sMonth;
        }

        if( date < 10 ) {
            sDate = "0"+sDate;
        }

        aDay[0] = sYear + sMonth + sDate;
        aDay[1] = sDay;

        return aDay;


    }//end method

    /**
     * 현재년을 구한다.
     * @author   이형중  | 2006-11-07
     */
    public static String getYear()
    {
        Calendar now = Calendar.getInstance();

        int year		= now.get(Calendar.YEAR);
        String sYear    = String.valueOf(year);

        return sYear;

    }//end method


    /**
     * 현재월을 구한다.
     * @author   이형중  | 2006-11-07
     */
    public static String getMonth()
    {
        Calendar now = Calendar.getInstance();

        int month= now.get(Calendar.MONTH)+1;

        String sMonth   = String.valueOf(month);

        if( month < 10 ) {
            sMonth = "0"+sMonth;
        }

        return sMonth;


    }//end method


    /**
     * 현재일을 구한다.
     * @author   이형중  | 2006-11-07
     * @return 현재일 ( ex: 2009-11-30 -> 30 )
     */
    public static String getDay()
    {
        Calendar now = Calendar.getInstance();

        int day= now.get(Calendar.DAY_OF_MONTH);

        String sDay   = String.valueOf(day);

        if( day < 10 ) {
            sDay = "0"+sDay;
        }

        return sDay;


    }//end method



    /**
     * 특정월의 분기를 구한다.
     * @param    month	분기를 구하고자하는 월
     * @return   String 	(1: 1분기, 2: 2분기, 3: 3분기, 4: 4분기)
     * @author   이형중  | 2006-11-07
     */
    public static String getQrt(String month)
    {
        int nMonth		= Integer.parseInt(month);
        String strQrt	= "";

        if(nMonth <= 3)
        {
            strQrt	= "1";
        }
        else if(nMonth <= 6)
        {
            strQrt	= "2";
        }
        else if(nMonth <= 9)
        {
            strQrt	= "3";
        }
        else
        {
            strQrt	= "4";
        }

        return strQrt;


    }//end method

    /**
     * 특정월의 분기를 구한다.
     * @param    month	분기를 구하고자하는 월
     * @return   String 	(1: 1분기, 2: 2분기, 3: 3분기, 4: 4분기)
     * @author   이형중  | 2006-11-07
     */
    public static String getToDate()
    {
        String new_date	= ""; //리턴할 분기

        try {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
            new_date = simpledateformat.format(new Date());
        }
        catch(Exception e) {
        }

        return new_date;
    }


    /**
     * :변수명으로 표시된 SQL을 preparedSQL을 사용하기 위해 ?로 변환한다.
     * @param    SQL	:변수명으로 표시된 SQL(예:SELECT * FROM DUAL WHERE DUMMY = :DUMMY_VALUE)
     * @return   String  (예:SELECT * FROM DUAL WHERE DUMMY = ?)
     * @author   정명수  | 2008-04-01
     */
    public static String getPreparedSQL(String SQL)
    {
        String strSQL = SQL.replaceAll("(?i::([^ ]+).*)", "?");

        return strSQL;
    }


    /**
     * 들어온 값을 날짜 형식으로 변환해준다 2009, 10, 12 -> 20091012 <br /> 달과 일이 2자리 넘어갈시에는
     *
     * @param Year 해당하는 년
     * @param Month 해당하는 달
     * @param Day 해당하는 날
     * @return ex)20091021
     */
    public static String getToDate(String Year, String Month, String Day) {
        StringBuffer sb = new StringBuffer();
        if (Year == null || Year.length() == 0 ) 		return "";
        if (Month == null || Month.length() == 0 ) 	return "";
        if (Day == null || Day.length() == 0 ) 			return "";

        if (Year.length() != 4  || Month.length() >= 3 || Day.length() >= 3)
            return "";
        sb.append(Year);
        sb.append((Month.length() ==1 )?"0"+1:Month );
        sb.append((Day.length() ==1 )?"0"+1:Day );
        return sb.toString();
    }


    /**
     * 들어온 값을 숫자 값으로 변환해준다.
     * @param val <b> int 타입으로 변환하</b>
     * @param toValue  val 값이 null 또는 정상적이지 않을때 대체되어서 return 될 값
     * @return  int (예: String str = null ; -> nullToInt(str, 100) ; 결과 : 100;
     */
    public static int nullToInt(String val, int toValue)
    {
        if (val == null || val.trim().equals(""))
            return toValue;
        int sval = 0;
        try {
            sval = Integer.parseInt(val);
        } catch (Exception e) {
            System.out.println("COMMUTIL nullToInt : "+ e.toString());
            sval = toValue;
        }

        return sval;
    }

    /**
     * 들어온 값을 SQL 에서 in 에서 사용할수 있도록 하기 위함. ex) String[] str ={ 1,2,3,4,5,6,7,8,9 } ->  1,2,3,4,5,6,7,8,9
     * @param arrValue String[] 값
     * @param mark  구분자
     * @return   String 로 넘어값. SQL 에서 where 조건에서 in 조건절에서 여러개 입력위함
     */
    public static String arrayToStringSQL (String[] arrValue ){
        if (arrValue == null || arrValue.length == 0 )
            return "";

        int arrSize = arrValue.length;
        StringBuffer sb = new StringBuffer();

        for ( int i = 0; i < arrSize ; ++i)
            sb.append("'").append(arrValue[i]).append("',");

        sb.delete(sb.length()-1, sb.length());

        return sb.toString();
    }


    /**
     * 들어온 값을 하나의 String 으로 반환 ex) String[] str ={ 1,2,3,4,5,6,7,8,9 } ->123456789
     * @param arrValue arrValue String[] 값
     * @return String 로 넘어값
     */
    public static String arrayToString(String[] arrValue) {
        if (arrValue == null || arrValue.length == 0 )
            return "";

        int arrSize = arrValue.length;
        StringBuffer sb = new StringBuffer();

        for ( int i = 0; i < arrSize ; ++i)
            sb.append(arrValue[i]);

        return sb.toString();
    }

    /**
     * 세금계산서에서 사업자 등록번호 또는 주민등록 번호일 경우 구분자 넣기
     * <br />사업자 번호 : 1231212345 -> 123-12-12345
     * <br />개인 주민번호 : 1234561234567 -> 123456-1234567
     * @param inputString 처리를 원하는 숫자
     * @return String 으로 반환  , Null 또는 "" 일경우. 숫자로 전환되지 않을경우 "" 으로 반환
     */
    public static String getTaxNumberView(String inputString){
        if (! isNumber(inputString)  )
            return "";

        if ( inputString.getBytes().length == 10  && inputString.length() ==10){//사업자 번호
            return  inputString.substring(0 ,3)+"-"
                    +inputString.substring(3 ,5) +"-"
                    +inputString.substring(5 ,10) ;

        }else if ( inputString.getBytes().length == 13 && inputString.length() ==13){//주민번호
            return  inputString.substring(0 ,6)+"-"
                    +inputString.substring(6 ,13) ;
        }else{
            return inputString;
        }
    }

    /**
     * 세금계산서에서 사업자 등록번호 또는 주민등록 번호일 경우 구분자 넣기(주민번호일 경우 *)
     * <br />사업자 번호 : 1231212345 -> 123-12-12345
     * <br />개인 주민번호 : 1234561234567 -> 123456-*******
     * @param inputString 처리를 원하는 숫자
     * @param division 구분자 ex) -
     * @return String 으로 반환  , Null 또는 "" 일경우. 숫자로 전환되지 않을경우 "" 으로 반환
     */
    public static String getTaxNumberStar(String inputString , String division){
        if (! isNumber(inputString)  )
            return "";

        if ( inputString.getBytes().length == 10  && inputString.length() ==10){//사업자 번호
            return  inputString.substring(0 ,3)+"-"
                    +inputString.substring(3 ,5) +"-"
                    +inputString.substring(5 ,10) ;

        }else if ( inputString.getBytes().length == 13 && inputString.length() ==13){//주민번호
            return  inputString.substring(0 ,6)+division+"*******" ;
        }else{
            return inputString;
        }

    }

    /**
     * 숫자인지 아닌지 판단
     * @param inputString 입력받은 숫자
     * @return  숫자이면 true , 문자면 false <br />Long 이상 자릿수 들어오면 false
     */

    public static boolean isNumber(String inputString){
        boolean flag = true;
        if ( inputString == null  ||    "".equals( inputString )  )
            return false;

        int size = inputString.length();
        int st_no= 0;

        if ( inputString.charAt(0)  ==  45 )//음수인지 아닌지 판별 . 음수면 시작위치를 1부터
            st_no = 1;

        for ( int i = st_no ; i < size ; ++i ){
            if ( !( 48   <=  ((int)inputString.charAt(i))   && 57>= ( (int)inputString.charAt(i) )  )  ){//asc 코드가 48~57범위이내가 아니면면
                flag = false;
                break;
            }
        }
        return flag;
    }
    /*		public static boolean isNumber(String inputString){
    //			String inputString = "1234567890123";
                if ( inputString == null || inputString.equals(""))
                    return false;
                try {
                    Long.parseLong(inputString);
                }catch (Exception e) {
                    return false;
                }
                return true;
            }
    */
    public static String getCurrentTimeFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(System.currentTimeMillis());
        String m_date = "";
        try {
            m_date = sdf.format(date);
        } catch (Exception e) {
            System.out.println("error in dateToString:" + e);
        }
        return m_date;
    }

    public static String moneyFormatComma(String value) { //20140312 compile된 소스 비교후 추가.
        //System.out.println(value);
        if(value == null || value.equals(""))
        {
            return "";
        } else
        {
            DecimalFormat dcf = new DecimalFormat("###,###,###,###,###,###.##");
            return dcf.format(Double.valueOf(value).doubleValue());
        }
    }

    // 20120820 박상종과장님 추가 메서드 입력 내용.
    public static String getHanParam(String psParam) {
        String HanParam = null;
        try {
            if (psParam != null) {
                HanParam = new String(psParam.getBytes("8859_1"), "KSC5601");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            return HanParam;
        }
    }


    public static String getComma(String str){

        String flag = "N";//소숫점 체크
        int iPos = 0;
        int p =0;
        String retVal = "";


        if (str.equals("") || str == null || str.equals("null")) {
            retVal = "";
        } else {

            iPos = str.length();

            for(int i=0; i<str.length(); i++){
                if(str.substring(i, (i+1)).equals(".")){
                    //System.out.println(i+"번째 소수점 발생");
                    flag = "Y";
                    iPos = i;
                }
            }

            StringBuffer sb = new StringBuffer(str.substring(0,iPos).toString());
            StringBuffer rsb = new StringBuffer();
            StringBuffer sbf = new StringBuffer(str.substring(iPos,str.length()));
            sb = sb.reverse();

            for (int i = 0; i < iPos; i++){
                p = i % 3;
                if(i > 0)
                    if (p == 0 && !sb.substring(i,(i+1)).equals("-"))
                        rsb.append(",");
                rsb.append(sb.substring(i, (i + 1)));
            }

            retVal = (rsb.reverse()).toString();

            if(flag.equals("Y")){
                retVal = retVal + sbf.toString();
            }
        }

        return retVal;
    }


    public static String TrimNull(String str){

        String returnStr = null;

        if (str == null || str.equals("null")){
            returnStr = "";
        }else{
            returnStr = str.trim();
        }

        return returnStr;
    }




}//END CLASS
