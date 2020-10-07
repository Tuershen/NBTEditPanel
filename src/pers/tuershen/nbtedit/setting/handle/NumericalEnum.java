package pers.tuershen.nbtedit.setting.handle;

import pers.tuershen.nbtedit.setting.NumericalConversion;

public enum NumericalEnum implements NumericalConversion {


    INT{
        @Override
        public Number conversion(String data) {
            Long l = Long.parseLong(data);
            if (l < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            if (l > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            return Integer.parseInt(data);
        }
    },
    BYTE{
        @Override
        public Number conversion(String data) {
            Long l = Long.parseLong(data);
            if (l < Byte.MIN_VALUE) return Byte.MIN_VALUE;
            if (l > Byte.MAX_VALUE) return Byte.MAX_VALUE;
            return Byte.valueOf(data);
        }
    },
    SHORT {
        @Override
        public Number conversion(String data) {
            Long l = Long.parseLong(data);
            if (l < Short.MIN_VALUE) return Short.MIN_VALUE;
            if (l > Short.MAX_VALUE) return Short.MAX_VALUE;
            return Short.parseShort(data);
        }
    },
    LONG {
        @Override
        public Number conversion(String data) {
            return Long.parseLong(data);
        }
    },
    DOUBLE{
        @Override
        public Number conversion(String data) {
            return Double.parseDouble(data);
        }
    },
    FLOAT {
        @Override
        public Number conversion(String data) {
            Double l = Double.parseDouble(data);
            if (l < Float.MIN_VALUE) return Float.MIN_VALUE;
            if (l > Float.MAX_VALUE) return Float.MAX_VALUE;
            return Float.parseFloat(data);
        }
    },




}
