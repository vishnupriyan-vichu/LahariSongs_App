package com.laharisongs.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookNameConstants {

    public static final String LANGUAGE_SELECTED = "language";
    public static final String TYPE_SELECTED = "typeSelected";

    public enum BooksConstant {
//        TAMIL_BOOKS(1, 1, new String[] {"SofR:-:= y`hp ehk kfpik fPjq;fs;", "IG:-:,ja fPjk;", "LN:-:= y`hp ehk rq;fPu;j;jdk;", "OTS:-:Njt ePjpapd; ghly;fs;", "SofRII:-:Njt ePjpapd; ghly;fs; - 2", "OTST:-:njYF ghly;fs;", "OTSM:-:kiyahsk;", "OTSC:-:gy;ytpfs;", "CMTA:-:$lhug; gz;bif - 2022"}),
//        TELUGU_BOOKS(2, 1, new String[] {"GN:-:గానామృతము", "PS:-:గురుదేవ్ శ్రీ లహరి నామ సంకీర్తనం", "LK:-:లహరి నామ కీర్తనలు", "CMTE:-:గుడారాల పండుగ - 2022"}),
//        ENGLISH_BOOKS(3, 1, new String[] {"ES:-:Enlgish songs", "EC:-:English chorus", "GHc:-:German and Hebrew chorus", "OTSE:-:Songs of God's Righteousness"}),
//        HINDI_BOOKS(4, 1, new String[] {"HS:-:లహరి నమ గీతము", "HP:-:గురుదేవ్ శ్రీ లహరి నామ సంకీర్తనం"}),
//        GITA_BOOK(5, 2, new String[] {"BG_E:-:", "BG_T:-:", "BG_TE:-:"})

        TAMIL_BOOKS(1, new String[]{BookKey.SOFR + ":-:= y`hp ehk kfpik fPjq;fs;", BookKey.IG + ":-:,ja fPjk;", BookKey.LN + ":-:= y`hp ehk rq;fPu;j;jdk;", BookKey.OTS + ":-:Njt ePjpapd; ghly;fs;", BookKey.SOFRII + ":-:Njt ePjpapd; ghly;fs; - 2", BookKey.OTST + ":-:njYF ghly;fs;", BookKey.OTSM + ":-:kiyahsk;", BookKey.OTSC + ":-:gy;ytpfs;", BookKey.CMTA + ":-:$lhug; gz;bif - 2022"}),
        TELUGU_BOOKS(2, new String[]{BookKey.GN + ":-:గానామృతము", BookKey.PS + ":-:గురుదేవ్ శ్రీ లహరి నామ సంకీర్తనం", BookKey.LK + ":-:లహరి నామ కీర్తనలు", BookKey.PK + ":-:పరమ కానాను గీతములు", BookKey.CMTE + ":-:గుడారాల పండుగ - 2022"}),
        ENGLISH_BOOKS(3, new String[]{BookKey.ES + ":-:Enlgish songs", BookKey.EC + ":-:English chorus", BookKey.GHc + ":-:German and Hebrew chorus", BookKey.OTSE + ":-:Songs of God's Righteousness"}),
        HINDI_BOOKS(4, new String[]{BookKey.HS + ":-:లహరి నమ గీతము", BookKey.HP + ":-:గురుదేవ్ శ్రీ లహరి నామ సంకీర్తనం"}),
        GITA_BOOK(5, new String[]{BookKey.BG_E + ":-:", BookKey.BG_TA + ":-:", BookKey.BG_TE + ":-:"});

        private static final Map<Integer, BooksConstant> booksLangMap = new HashMap<>();

        static {
            for (BooksConstant books : BooksConstant.values()) {
                booksLangMap.put(books.lang, books);
            }
        }

        private final int lang;
        private final Map<String, String> booksMap = new HashMap<>();
        private final List<String> bookKeys = new ArrayList<>();

        BooksConstant(int lang, String[] books) {
            this.lang = lang;
            for (String book : books) {
                String[] bookMap = book.split(":-:");
                String bookKey = bookMap[0].trim();
                if (bookMap.length > 1) {
                    String bookName = bookMap[1].trim();
                    booksMap.put(bookKey, bookName);
                } else {
                    booksMap.put(bookKey, "");
                }
                bookKeys.add(bookKey);
            }
        }

        public static BooksConstant getBooks(int lang) {
            return booksLangMap.get(lang);
        }

        public int getLang() {
            return lang;
        }

        public String getBookName(String bookKey) {
            return booksMap.get(bookKey);
        }

        public List<String> getBookKeys() {
            return bookKeys;
        }
    }

    public static class BookKey {
        public static final String SOFR = "SofR";
        public static final String IG = "IG";
        public static final String LN = "LN";
        public static final String OTS = "OTS";
        public static final String SOFRII = "SofRII";
        public static final String OTST = "OTST";
        public static final String OTSM = "OTSM";
        public static final String OTSC = "OTSC";
        public static final String CMTA = "CMTA";
        public static final String GN = "GN";
        public static final String PS = "PS";
        public static final String LK = "LK";
        public static final String PK = "PK-G";
        public static final String CMTE = "CMTE";
        public static final String ES = "ES";
        public static final String EC = "EC";
        public static final String GHc = "GHc";
        public static final String OTSE = "OTSE";
        public static final String HS = "HS";
        public static final String HP = "HP";
        public static final String BG_E = "BG_E";
        public static final String BG_TE = "BG_TE";
        public static final String BG_TA = "BG_T";
    }

    public static class LangKey {
        public static final String TAMIL = "TAMIL_BOOKS";
        public static final String TELUGU = "TELUGU_BOOKS";
        public static final String ENGLISH = "ENGLISH_BOOKS";
        public static final String HINDI = "HINDI_BOOKS";
    }
}