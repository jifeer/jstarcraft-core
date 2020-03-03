package com.jstarcraft.core.common.instant;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 阴历日期
 * 
 * @author Birdy
 *
 */
public class LunarDate {

    /**
     * 支持的最小年份
     */
    public final static int MINIMUM_YEAR = 1850;

    /**
     * 支持的最大年份
     */
    public final static int MAXIMUM_YEAR = 2150;

    /**
     * <pre>
     * 1850年-2150年间的农历信息
     * 每个数组的第一个数表示该年闰月月份,为0表示不闰月
     * 数组中其它数表示该月初一对应的公历日期
     * </pre>
     */
    private final static int[][] lunar2Solar = {

            { 0, 212, 314, 412, 512, 610, 709, 808, 906, 1005, 1104, 1204, 1302 }, // 1850
            { 8, 201, 303, 402, 501, 531, 629, 728, 827, 925, 1024, 1123, 1222, 1321 }, // 1851
            { 0, 220, 321, 331, 419, 519, 618, 717, 815, 914, 1013, 1112, 1211, 1309 }, // 1852
            { 0, 208, 310, 408, 508, 607, 706, 805, 903, 1003, 1101, 1201, 1230 }, // 1853
            { 7, 129, 227, 329, 427, 527, 625, 725, 824, 922, 1022, 1120, 1220, 1318 }, // 1854
            { 0, 217, 318, 416, 516, 614, 714, 813, 911, 1011, 1110, 1209, 1308 }, // 1855
            { 0, 206, 307, 405, 504, 603, 702, 801, 830, 929, 1029, 1128, 1227 }, // 1856
            { 5, 126, 224, 326, 424, 523, 622, 721, 820, 918, 1018, 1116, 1216, 1315 }, // 1857
            { 0, 214, 315, 414, 513, 611, 711, 809, 907, 1007, 1106, 1205, 1304 }, // 1858
            { 0, 203, 305, 403, 503, 601, 630, 730, 828, 926, 1026, 1124, 1224 }, // 1859
            { 3, 123, 222, 322, 421, 521, 619, 718, 817, 915, 1014, 1113, 1212, 1311 }, // 1860
            { 0, 210, 311, 410, 510, 608, 708, 806, 905, 1004, 1103, 1202, 1231 }, // 1861
            { 8, 130, 301, 330, 429, 528, 627, 727, 825, 924, 1023, 1122, 1221, 1319 }, // 1862
            { 0, 218, 319, 418, 518, 616, 716, 814, 913, 1013, 1111, 1211, 1309 }, // 1863
            { 0, 208, 308, 406, 506, 604, 704, 802, 901, 1001, 1030, 1129, 1229, 1327 }, // 1864
            { 6, 127, 226, 327, 425, 525, 623, 723, 821, 920, 1020, 1118, 1218, 1317 }, // 1865
            { 0, 215, 317, 415, 514, 613, 712, 810, 909, 1009, 1107, 1207, 1306 }, // 1866
            { 0, 205, 306, 405, 504, 602, 702, 731, 829, 928, 1027, 1126, 1226 }, // 1867
            { 4, 125, 223, 324, 423, 522, 620, 720, 818, 916, 1016, 1114, 1214, 1313 }, // 1868
            { 0, 211, 313, 412, 512, 610, 709, 808, 906, 1005, 1104, 1203, 1302 }, // 1869
            { 10, 131, 302, 401, 501, 530, 629, 728, 827, 925, 1024, 1123, 1222, 1321 }, // 1870
            { 0, 219, 321, 420, 519, 618, 718, 816, 915, 1014, 1113, 1212, 1310 }, // 1871
            { 0, 209, 309, 408, 507, 606, 706, 804, 903, 1002, 1101, 1201, 1230 }, // 1872
            { 6, 129, 227, 328, 427, 526, 625, 724, 823, 922, 1021, 1120, 1220, 1318 }, // 1873
            { 0, 217, 318, 416, 516, 614, 714, 812, 911, 1010, 1109, 1209, 1308 }, // 1874
            { 0, 206, 308, 406, 505, 604, 703, 801, 831, 929, 1029, 1128, 1228 }, // 1875
            { 5, 126, 225, 326, 424, 523, 622, 721, 819, 918, 1017, 1116, 1216, 1314 }, // 1876
            { 0, 213, 315, 414, 513, 611, 711, 809, 907, 1007, 1105, 1205, 1303 }, // 1877
            { 0, 202, 304, 403, 502, 601, 630, 730, 828, 926, 1026, 1124, 1224 }, // 1878
            { 3, 122, 221, 323, 421, 521, 620, 719, 818, 916, 1015, 1114, 1213, 1312 }, // 1879
            { 0, 210, 311, 409, 509, 608, 707, 806, 905, 1004, 1103, 1202, 1231 }, // 1880
            { 7, 130, 228, 330, 428, 528, 626, 726, 825, 923, 1023, 1122, 1221, 1320 }, // 1881
            { 0, 218, 319, 418, 517, 616, 715, 814, 912, 1012, 1111, 1210, 1309 }, // 1882
            { 0, 208, 309, 407, 507, 605, 704, 803, 901, 1001, 1031, 1130, 1229 }, // 1883
            { 5, 128, 227, 327, 425, 525, 623, 722, 821, 919, 1019, 1118, 1217, 1316 }, // 1884
            { 0, 215, 317, 415, 514, 613, 712, 810, 909, 1008, 1107, 1206, 1305 }, // 1885
            { 0, 204, 306, 404, 504, 602, 702, 731, 829, 928, 1027, 1126, 1225 }, // 1886
            { 4, 124, 223, 325, 423, 523, 621, 721, 819, 917, 1017, 1115, 1215, 1313 }, // 1887
            { 0, 212, 313, 411, 511, 610, 709, 808, 906, 1005, 1104, 1203, 1302 }, // 1888
            { 12, 131, 302, 331, 430, 530, 628, 728, 826, 925, 1024, 1123, 1222, 1321 }, // 1889
            { 0, 219, 321, 419, 519, 617, 717, 816, 914, 1014, 1112, 1212, 1310 }, // 1890
            { 0, 209, 310, 409, 508, 607, 706, 805, 903, 1003, 1102, 1201, 1231 }, // 1891
            { 6, 130, 228, 328, 427, 526, 624, 724, 822, 921, 1021, 1119, 1219, 1318 }, // 1892
            { 0, 217, 318, 416, 516, 614, 713, 812, 910, 1010, 1108, 1208, 1307 }, // 1893
            { 0, 206, 307, 406, 505, 604, 703, 801, 831, 929, 1029, 1127, 1227 }, // 1894
            { 5, 126, 225, 326, 425, 524, 623, 722, 820, 919, 1018, 1117, 1216, 1315 }, // 1895
            { 0, 214, 314, 413, 513, 611, 711, 809, 907, 1007, 1105, 1205, 1303 }, // 1896
            { 0, 202, 303, 402, 502, 531, 630, 730, 828, 926, 1026, 1124, 1224 }, // 1897
            { 3, 122, 221, 322, 421, 520, 619, 719, 817, 916, 1015, 1114, 1213, 1312 }, // 1898
            { 0, 210, 312, 410, 510, 608, 708, 806, 905, 1005, 1103, 1203, 1301 }, // 1899
            { 8, 131, 301, 331, 429, 528, 627, 726, 825, 924, 1023, 1122, 1222, 1320 }, // 1900
            { 0, 219, 320, 419, 518, 616, 716, 814, 913, 1012, 1111, 1211, 1310 }, // 1901
            { 0, 208, 310, 408, 508, 606, 705, 804, 902, 1002, 1031, 1130, 1230 }, // 1902
            { 5, 129, 227, 329, 427, 527, 625, 724, 823, 921, 1020, 1119, 1219, 1317 }, // 1903
            { 0, 216, 317, 416, 515, 614, 713, 811, 910, 1009, 1107, 1207, 1306 }, // 1904
            { 0, 204, 306, 405, 504, 603, 703, 801, 830, 929, 1028, 1127, 1226 }, // 1905
            { 4, 125, 223, 325, 424, 523, 622, 721, 820, 918, 1018, 1116, 1216, 1314 }, // 1906
            { 0, 213, 314, 413, 512, 611, 710, 809, 908, 1007, 1106, 1205, 1304 }, // 1907
            { 0, 202, 303, 401, 430, 530, 629, 728, 827, 925, 1025, 1124, 1223 }, // 1908
            { 2, 122, 220, 322, 420, 519, 618, 717, 816, 914, 1014, 1113, 1213, 1311 }, // 1909
            { 0, 210, 311, 410, 509, 607, 707, 805, 904, 1003, 1102, 1202, 1301 }, // 1910
            { 6, 130, 301, 330, 429, 528, 626, 726, 824, 922, 1022, 1121, 1220, 1319 }, // 1911
            { 0, 218, 319, 417, 517, 615, 714, 813, 911, 1010, 1109, 1209, 1307 }, // 1912
            { 0, 206, 308, 407, 506, 605, 704, 802, 901, 930, 1029, 1128, 1227 }, // 1913
            { 5, 126, 225, 327, 425, 525, 623, 723, 821, 920, 1019, 1117, 1217, 1315 }, // 1914
            { 0, 214, 316, 414, 514, 613, 712, 811, 909, 1009, 1107, 1207, 1305 }, // 1915
            { 0, 203, 304, 403, 502, 601, 630, 730, 829, 927, 1027, 1125, 1225 }, // 1916
            { 2, 123, 222, 323, 421, 521, 619, 719, 818, 916, 1016, 1115, 1214, 1313 }, // 1917
            { 0, 211, 313, 411, 510, 609, 708, 807, 905, 1005, 1104, 1203, 1302 }, // 1918
            { 7, 201, 302, 401, 430, 529, 628, 727, 825, 924, 1024, 1122, 1222, 1321 }, // 1919
            { 0, 220, 320, 419, 518, 616, 716, 814, 912, 1012, 1110, 1210, 1309 }, // 1920
            { 0, 208, 310, 408, 508, 606, 705, 804, 902, 1001, 1031, 1129, 1229 }, // 1921
            { 5, 128, 227, 328, 427, 527, 625, 724, 823, 921, 1020, 1119, 1218, 1317 }, // 1922
            { 0, 216, 317, 416, 516, 614, 714, 812, 911, 1010, 1108, 1208, 1306 }, // 1923
            { 0, 205, 305, 404, 504, 602, 702, 801, 830, 929, 1028, 1127, 1226 }, // 1924
            { 4, 124, 223, 324, 423, 522, 621, 721, 819, 918, 1018, 1116, 1216, 1314 }, // 1925
            { 0, 213, 314, 412, 512, 610, 710, 808, 907, 1007, 1105, 1205, 1304 }, // 1926
            { 0, 202, 304, 402, 501, 531, 629, 729, 827, 926, 1025, 1124, 1224 }, // 1927
            { 2, 123, 221, 322, 420, 519, 618, 717, 815, 914, 1013, 1112, 1212, 1311 }, // 1928
            { 0, 210, 311, 410, 509, 607, 707, 805, 903, 1003, 1101, 1201, 1231 }, // 1929
            { 6, 130, 228, 330, 429, 528, 626, 726, 824, 922, 1022, 1120, 1220, 1319 }, // 1930
            { 0, 217, 319, 418, 517, 616, 715, 814, 912, 1011, 1110, 1209, 1308 }, // 1931
            { 0, 206, 307, 406, 506, 604, 704, 802, 901, 930, 1029, 1128, 1227 }, // 1932
            { 5, 126, 224, 326, 425, 524, 623, 722, 821, 920, 1019, 1118, 1217, 1315 }, // 1933
            { 0, 214, 315, 414, 513, 612, 712, 810, 909, 1008, 1107, 1207, 1305 }, // 1934
            { 0, 204, 305, 403, 503, 601, 701, 730, 829, 928, 1027, 1126, 1226 }, // 1935
            { 3, 124, 223, 323, 421, 521, 619, 718, 817, 916, 1015, 1114, 1214, 1313 }, // 1936
            { 0, 211, 313, 411, 510, 609, 708, 806, 905, 1004, 1103, 1203, 1302 }, // 1937
            { 7, 131, 302, 401, 430, 529, 628, 727, 825, 924, 1023, 1122, 1222, 1320 }, // 1938
            { 0, 219, 321, 420, 519, 617, 717, 815, 913, 1013, 1111, 1211, 1309 }, // 1939
            { 0, 208, 309, 408, 507, 606, 705, 804, 902, 1001, 1031, 1129, 1229 }, // 1940
            { 6, 127, 226, 328, 426, 526, 625, 724, 823, 921, 1020, 1119, 1218, 1317 }, // 1941
            { 0, 215, 317, 415, 515, 614, 713, 812, 910, 1010, 1108, 1208, 1306 }, // 1942
            { 0, 205, 306, 405, 504, 603, 702, 801, 831, 929, 1029, 1127, 1227 }, // 1943
            { 4, 125, 224, 324, 423, 522, 621, 720, 819, 917, 1017, 1116, 1215, 1314 }, // 1944
            { 0, 213, 314, 412, 512, 610, 709, 808, 906, 1006, 1105, 1205, 1303 }, // 1945
            { 0, 202, 304, 402, 501, 531, 629, 728, 827, 925, 1025, 1124, 1223 }, // 1946
            { 2, 122, 221, 323, 421, 520, 619, 718, 816, 915, 1014, 1113, 1212, 1311 }, // 1947
            { 0, 210, 311, 409, 509, 607, 707, 805, 903, 1003, 1101, 1201, 1230 }, // 1948
            { 7, 129, 228, 329, 428, 528, 626, 726, 824, 922, 1022, 1120, 1220, 1318 }, // 1949
            { 0, 217, 318, 417, 517, 615, 715, 814, 912, 1011, 1110, 1209, 1308 }, // 1950
            { 0, 206, 308, 406, 506, 605, 704, 803, 901, 1001, 1030, 1129, 1228 }, // 1951
            { 5, 127, 225, 326, 424, 524, 622, 722, 820, 919, 1019, 1117, 1217, 1315 }, // 1952
            { 0, 214, 315, 414, 513, 611, 711, 810, 908, 1008, 1107, 1206, 1305 }, // 1953
            { 0, 203, 305, 403, 503, 601, 630, 730, 828, 927, 1027, 1126, 1225 }, // 1954
            { 3, 124, 222, 324, 422, 522, 620, 719, 818, 916, 1016, 1114, 1214, 1313 }, // 1955
            { 0, 212, 312, 411, 510, 609, 708, 806, 905, 1004, 1103, 1203, 1301 }, // 1956
            { 8, 131, 302, 331, 430, 529, 628, 727, 825, 924, 1023, 1122, 1221, 1320 }, // 1957
            { 0, 218, 320, 419, 519, 617, 717, 815, 913, 1013, 1111, 1211, 1309 }, // 1958
            { 0, 208, 309, 408, 508, 606, 706, 804, 903, 1002, 1101, 1130, 1230 }, // 1959
            { 6, 128, 227, 327, 426, 525, 624, 724, 822, 921, 1020, 1119, 1218, 1317 }, // 1960
            { 0, 215, 317, 415, 515, 613, 713, 811, 910, 1010, 1108, 1208, 1306 }, // 1961
            { 0, 205, 306, 405, 504, 602, 702, 731, 830, 929, 1028, 1127, 1227 }, // 1962
            { 4, 125, 224, 325, 424, 523, 621, 721, 819, 918, 1017, 1116, 1216, 1315 }, // 1963
            { 0, 213, 314, 412, 512, 610, 709, 808, 906, 1006, 1104, 1204, 1303 }, // 1964
            { 0, 202, 303, 402, 501, 531, 629, 728, 827, 925, 1024, 1123, 1223 }, // 1965
            { 3, 121, 220, 322, 421, 520, 619, 718, 816, 915, 1014, 1112, 1212, 1311 }, // 1966
            { 0, 209, 311, 410, 509, 608, 708, 806, 904, 1004, 1102, 1202, 1231 }, // 1967
            { 7, 130, 228, 329, 427, 527, 626, 725, 824, 922, 1022, 1120, 1220, 1318 }, // 1968
            { 0, 217, 318, 417, 516, 615, 714, 813, 912, 1011, 1110, 1209, 1308 }, // 1969
            { 0, 206, 308, 406, 505, 604, 703, 802, 901, 930, 1030, 1129, 1228 }, // 1970
            { 5, 127, 225, 327, 425, 524, 623, 722, 821, 919, 1019, 1118, 1218, 1316 }, // 1971
            { 0, 215, 315, 414, 513, 611, 711, 809, 908, 1007, 1106, 1206, 1304 }, // 1972
            { 0, 203, 305, 403, 503, 601, 630, 730, 828, 926, 1026, 1125, 1224 }, // 1973
            { 4, 123, 222, 324, 422, 522, 620, 719, 818, 916, 1015, 1114, 1214, 1312 }, // 1974
            { 0, 211, 313, 412, 511, 610, 709, 807, 906, 1005, 1103, 1203, 1301 }, // 1975
            { 8, 131, 301, 331, 429, 529, 627, 727, 825, 924, 1023, 1121, 1221, 1319 }, // 1976
            { 0, 218, 320, 418, 518, 617, 716, 815, 913, 1013, 1111, 1211, 1309 }, // 1977
            { 0, 207, 309, 407, 507, 606, 705, 804, 902, 1002, 1101, 1130, 1230 }, // 1978
            { 6, 128, 227, 328, 426, 526, 624, 724, 823, 921, 1021, 1120, 1219, 1318 }, // 1979
            { 0, 216, 317, 415, 514, 613, 712, 811, 909, 1009, 1108, 1207, 1306 }, // 1980
            { 0, 205, 306, 405, 504, 602, 702, 731, 829, 928, 1028, 1126, 1226 }, // 1981
            { 4, 125, 224, 325, 424, 523, 621, 721, 819, 917, 1017, 1115, 1215, 1314 }, // 1982
            { 0, 213, 315, 413, 513, 611, 710, 809, 907, 1006, 1105, 1204, 1303 }, // 1983
            { 10, 202, 303, 401, 501, 531, 629, 728, 827, 925, 1024, 1123, 1222, 1321 }, // 1984
            { 0, 220, 321, 420, 520, 618, 718, 816, 915, 1014, 1112, 1212, 1310 }, // 1985
            { 0, 209, 310, 409, 509, 607, 707, 806, 904, 1004, 1102, 1202, 1231 }, // 1986
            { 6, 129, 228, 329, 428, 527, 626, 726, 824, 923, 1023, 1121, 1221, 1319 }, // 1987
            { 0, 217, 318, 416, 516, 614, 714, 812, 911, 1011, 1109, 1209, 1308 }, // 1988
            { 0, 206, 308, 406, 505, 604, 703, 802, 831, 930, 1029, 1128, 1228 }, // 1989
            { 5, 127, 225, 327, 425, 524, 623, 722, 820, 919, 1018, 1117, 1217, 1316 }, // 1990
            { 0, 215, 316, 415, 514, 612, 712, 810, 908, 1008, 1106, 1206, 1305 }, // 1991
            { 0, 204, 304, 403, 503, 601, 630, 730, 828, 926, 1026, 1124, 1224 }, // 1992
            { 3, 123, 221, 323, 422, 521, 620, 719, 818, 916, 1015, 1114, 1213, 1312 }, // 1993
            { 0, 210, 312, 411, 511, 609, 709, 807, 906, 1005, 1103, 1203, 1301 }, // 1994
            { 8, 131, 301, 331, 430, 529, 628, 727, 826, 925, 1024, 1122, 1222, 1320 }, // 1995
            { 0, 219, 319, 418, 517, 616, 715, 814, 912, 1012, 1111, 1211, 1309 }, // 1996
            { 0, 207, 309, 407, 507, 605, 705, 803, 902, 1002, 1031, 1130, 1230 }, // 1997
            { 5, 128, 227, 328, 426, 526, 624, 723, 822, 921, 1020, 1119, 1219, 1317 }, // 1998
            { 0, 216, 318, 416, 515, 614, 713, 811, 910, 1009, 1108, 1208, 1307 }, // 1999
            { 0, 205, 306, 405, 504, 602, 702, 731, 829, 928, 1027, 1126, 1226 }, // 2000
            { 4, 124, 223, 325, 423, 523, 621, 721, 819, 917, 1017, 1115, 1215, 1313 }, // 2001
            { 0, 212, 314, 413, 512, 611, 710, 809, 907, 1006, 1105, 1204, 1303 }, // 2002
            { 0, 201, 303, 402, 501, 531, 630, 729, 828, 926, 1025, 1124, 1223 }, // 2003
            { 2, 122, 220, 321, 419, 519, 618, 717, 816, 914, 1014, 1112, 1212, 1310 }, // 2004
            { 0, 209, 310, 409, 508, 607, 706, 805, 904, 1003, 1102, 1201, 1231 }, // 2005
            { 7, 129, 228, 329, 428, 527, 626, 725, 824, 922, 1022, 1121, 1220, 1319 }, // 2006
            { 0, 218, 319, 417, 517, 615, 714, 813, 911, 1011, 1110, 1210, 1308 }, // 2007
            { 0, 207, 308, 406, 505, 604, 703, 801, 831, 929, 1029, 1128, 1227 }, // 2008
            { 5, 126, 225, 327, 425, 524, 623, 722, 820, 919, 1018, 1117, 1216, 1315 }, // 2009
            { 0, 214, 316, 414, 514, 612, 712, 810, 908, 1008, 1106, 1206, 1304 }, // 2010
            { 0, 203, 305, 403, 503, 602, 701, 731, 829, 927, 1027, 1125, 1225 }, // 2011
            { 4, 123, 222, 322, 421, 521, 619, 719, 817, 916, 1015, 1114, 1213, 1312 }, // 2012
            { 0, 210, 312, 410, 510, 608, 708, 807, 905, 1005, 1103, 1203, 1301 }, // 2013
            { 9, 131, 301, 331, 429, 529, 627, 727, 825, 924, 1024, 1122, 1222, 1320 }, // 2014
            { 0, 219, 320, 419, 518, 616, 716, 814, 913, 1013, 1112, 1211, 1310 }, // 2015
            { 0, 208, 309, 407, 507, 605, 704, 803, 901, 1001, 1031, 1129, 1229 }, // 2016
            { 6, 128, 226, 328, 426, 526, 624, 723, 822, 920, 1020, 1118, 1218, 1317 }, // 2017
            { 0, 216, 317, 416, 515, 614, 713, 811, 910, 1009, 1108, 1207, 1306 }, // 2018
            { 0, 205, 307, 405, 505, 603, 703, 801, 830, 929, 1028, 1126, 1226 }, // 2019
            { 4, 125, 223, 324, 423, 523, 621, 721, 819, 917, 1017, 1115, 1215, 1313 }, // 2020
            { 0, 212, 313, 412, 512, 610, 710, 808, 907, 1006, 1105, 1204, 1303 }, // 2021
            { 0, 201, 303, 401, 501, 530, 629, 729, 827, 926, 1025, 1124, 1223 }, // 2022
            { 2, 122, 220, 322, 420, 519, 618, 718, 816, 915, 1015, 1113, 1213, 1311 }, // 2023
            { 0, 210, 310, 409, 508, 606, 706, 804, 903, 1003, 1101, 1201, 1231 }, // 2024
            { 6, 129, 228, 329, 428, 527, 625, 725, 823, 922, 1021, 1120, 1220, 1319 }, // 2025
            { 0, 217, 319, 417, 517, 615, 714, 813, 911, 1010, 1109, 1209, 1308 }, // 2026
            { 0, 206, 308, 407, 506, 605, 704, 802, 901, 930, 1029, 1128, 1228 }, // 2027
            { 5, 126, 225, 326, 425, 524, 623, 722, 820, 919, 1018, 1116, 1216, 1315 }, // 2028
            { 0, 213, 315, 414, 513, 612, 711, 810, 908, 1008, 1106, 1205, 1304 }, // 2029
            { 0, 203, 304, 403, 502, 601, 701, 730, 829, 927, 1027, 1125, 1225 }, // 2030
            { 3, 123, 221, 323, 422, 521, 620, 719, 818, 917, 1016, 1115, 1214, 1313 }, // 2031
            { 0, 211, 312, 410, 509, 608, 707, 806, 905, 1004, 1103, 1203, 1301 }, // 2032
            { 7, 131, 301, 331, 429, 528, 627, 726, 825, 923, 1023, 1122, 1222, 1320 }, // 2033
            { 0, 219, 320, 419, 518, 616, 716, 814, 913, 1012, 1111, 1211, 1309 }, // 2034
            { 0, 208, 310, 408, 508, 606, 705, 804, 902, 1001, 1031, 1130, 1229 }, // 2035
            { 6, 128, 227, 328, 426, 526, 624, 723, 822, 920, 1019, 1118, 1217, 1316 }, // 2036
            { 0, 215, 317, 416, 515, 614, 713, 811, 910, 1009, 1107, 1207, 1305 }, // 2037
            { 0, 204, 306, 405, 504, 603, 702, 801, 830, 929, 1028, 1126, 1226 }, // 2038
            { 5, 124, 223, 325, 423, 523, 622, 721, 820, 918, 1018, 1116, 1216, 1314 }, // 2039
            { 0, 212, 313, 411, 511, 610, 709, 808, 906, 1006, 1105, 1204, 1303 }, // 2040
            { 0, 201, 302, 401, 430, 530, 628, 728, 827, 925, 1025, 1124, 1223 }, // 2041
            { 2, 122, 220, 322, 420, 519, 618, 717, 816, 914, 1014, 1113, 1212, 1311 }, // 2042
            { 0, 210, 311, 410, 509, 607, 707, 805, 903, 1003, 1102, 1201, 1231 }, // 2043
            { 7, 130, 229, 329, 428, 527, 625, 725, 823, 921, 1021, 1119, 1219, 1318 }, // 2044
            { 0, 217, 319, 417, 517, 615, 714, 813, 911, 1010, 1109, 1208, 1307 }, // 2045
            { 0, 206, 308, 406, 506, 604, 704, 802, 901, 930, 1029, 1128, 1227 }, // 2046
            { 5, 126, 225, 326, 425, 525, 623, 723, 821, 920, 1019, 1117, 1217, 1315 }, // 2047
            { 0, 214, 314, 413, 513, 611, 711, 810, 908, 1008, 1106, 1205, 1304 }, // 2048
            { 0, 202, 304, 402, 502, 531, 630, 730, 828, 927, 1027, 1125, 1225 }, // 2049
            { 3, 123, 221, 323, 421, 521, 619, 719, 817, 916, 1016, 1114, 1214, 1313 }, // 2050
            { 0, 211, 313, 411, 510, 609, 708, 806, 905, 1005, 1103, 1203, 1302 }, // 2051
            { 8, 201, 301, 331, 429, 528, 627, 726, 824, 923, 1022, 1121, 1221, 1320 }, // 2052
            { 0, 219, 320, 419, 518, 616, 716, 814, 912, 1012, 1110, 1210, 1309 }, // 2053
            { 0, 208, 309, 408, 508, 606, 705, 804, 902, 1001, 1031, 1129, 1229 }, // 2054
            { 6, 128, 226, 328, 427, 526, 625, 724, 823, 921, 1020, 1119, 1218, 1317 }, // 2055
            { 0, 215, 316, 415, 515, 613, 713, 811, 910, 1009, 1107, 1207, 1305 }, // 2056
            { 0, 204, 305, 404, 504, 602, 702, 731, 830, 929, 1028, 1126, 1226 }, // 2057
            { 4, 124, 223, 324, 423, 522, 621, 720, 819, 918, 1017, 1116, 1216, 1314 }, // 2058
            { 0, 212, 314, 412, 512, 610, 710, 808, 907, 1006, 1105, 1205, 1304 }, // 2059
            { 0, 202, 303, 401, 501, 530, 628, 727, 826, 924, 1024, 1123, 1223 }, // 2060
            { 3, 121, 220, 322, 420, 519, 618, 717, 815, 914, 1013, 1112, 1212, 1311 }, // 2061
            { 0, 209, 311, 410, 509, 607, 707, 805, 903, 1003, 1101, 1201, 1231 }, // 2062
            { 7, 129, 228, 330, 428, 528, 626, 726, 824, 922, 1022, 1120, 1220, 1318 }, // 2063
            { 0, 217, 318, 417, 516, 615, 714, 813, 911, 1010, 1109, 1208, 1307 }, // 2064
            { 0, 205, 307, 406, 505, 604, 704, 802, 901, 930, 1029, 1128, 1227 }, // 2065
            { 5, 126, 224, 326, 424, 524, 623, 722, 821, 919, 1019, 1117, 1217, 1315 }, // 2066
            { 0, 214, 315, 414, 513, 612, 711, 810, 909, 1008, 1107, 1206, 1305 }, // 2067
            { 0, 203, 304, 402, 502, 531, 629, 729, 828, 926, 1026, 1125, 1224 }, // 2068
            { 4, 123, 221, 323, 421, 521, 619, 718, 817, 915, 1015, 1114, 1214, 1312 }, // 2069
            { 0, 211, 312, 411, 510, 609, 708, 806, 905, 1004, 1103, 1203, 1301 }, // 2070
            { 8, 131, 302, 331, 430, 529, 628, 727, 825, 924, 1023, 1122, 1221, 1320 }, // 2071
            { 0, 219, 320, 418, 518, 616, 716, 814, 912, 1012, 1110, 1210, 1308 }, // 2072
            { 0, 207, 309, 407, 507, 606, 705, 804, 902, 1001, 1031, 1129, 1229 }, // 2073
            { 6, 127, 226, 327, 426, 526, 624, 724, 822, 921, 1020, 1119, 1218, 1317 }, // 2074
            { 0, 215, 317, 415, 515, 613, 713, 812, 910, 1010, 1108, 1208, 1306 }, // 2075
            { 0, 205, 305, 404, 503, 602, 701, 731, 829, 928, 1028, 1126, 1226 }, // 2076
            { 4, 124, 223, 324, 423, 522, 620, 720, 818, 917, 1017, 1116, 1215, 1314 }, // 2077
            { 0, 212, 314, 412, 512, 610, 709, 808, 906, 1006, 1105, 1204, 1303 }, // 2078
            { 0, 202, 303, 402, 501, 531, 629, 728, 827, 925, 1025, 1123, 1223 }, // 2079
            { 3, 122, 221, 321, 420, 519, 618, 717, 815, 914, 1013, 1111, 1211, 1310 }, // 2080
            { 0, 209, 310, 409, 509, 607, 707, 805, 903, 1003, 1101, 1130, 1230 }, // 2081
            { 7, 129, 227, 329, 428, 528, 626, 725, 824, 922, 1022, 1120, 1219, 1318 }, // 2082
            { 0, 217, 318, 417, 517, 615, 715, 813, 912, 1011, 1110, 1209, 1308 }, // 2083
            { 0, 206, 307, 405, 505, 603, 703, 802, 831, 930, 1029, 1128, 1227 }, // 2084
            { 5, 126, 224, 326, 424, 523, 622, 722, 820, 919, 1019, 1117, 1217, 1315 }, // 2085
            { 0, 214, 315, 414, 513, 611, 711, 809, 908, 1008, 1106, 1206, 1305 }, // 2086
            { 0, 203, 305, 403, 503, 601, 630, 730, 828, 927, 1026, 1125, 1225 }, // 2087
            { 4, 124, 222, 323, 421, 521, 619, 718, 817, 915, 1014, 1113, 1213, 1312 }, // 2088
            { 0, 210, 312, 411, 510, 609, 708, 806, 904, 1004, 1102, 1202, 1301 }, // 2089
            { 8, 130, 301, 331, 430, 529, 628, 727, 825, 924, 1023, 1121, 1221, 1320 }, // 2090
            { 0, 218, 320, 419, 518, 617, 716, 815, 913, 1013, 1111, 1210, 1309 }, // 2091
            { 0, 207, 308, 407, 506, 605, 705, 803, 902, 1001, 1031, 1129, 1229 }, // 2092
            { 6, 127, 225, 327, 426, 525, 624, 723, 822, 921, 1020, 1119, 1218, 1317 }, // 2093
            { 0, 215, 316, 415, 514, 613, 712, 811, 910, 1009, 1108, 1208, 1306 }, // 2094
            { 0, 205, 306, 405, 504, 602, 702, 731, 830, 928, 1028, 1127, 1227 }, // 2095
            { 4, 125, 224, 324, 423, 522, 620, 720, 818, 916, 1016, 1115, 1215, 1313 }, // 2096
            { 0, 212, 314, 412, 512, 610, 709, 808, 906, 1005, 1104, 1204, 1302 }, // 2097
            { 0, 201, 303, 402, 501, 531, 629, 728, 826, 925, 1024, 1123, 1222 }, // 2098
            { 2, 121, 220, 322, 420, 520, 619, 718, 816, 915, 1014, 1112, 1212, 1310 }, // 2099
            { 0, 209, 311, 410, 509, 608, 707, 806, 904, 1004, 1102, 1201, 1231 }, // 2100
            { 7, 129, 228, 330, 428, 528, 626, 726, 825, 923, 1023, 1121, 1220, 1319 }, // 2101
            { 0, 217, 319, 417, 517, 616, 715, 814, 912, 1012, 1111, 1210, 1309 }, // 2102
            { 0, 207, 308, 407, 506, 605, 704, 803, 901, 1001, 1031, 1130, 1229 }, // 2103
            { 5, 128, 226, 327, 425, 524, 623, 722, 821, 919, 1019, 1118, 1217, 1316 }, // 2104
            { 0, 215, 316, 415, 514, 612, 712, 810, 908, 1008, 1107, 1206, 1305 }, // 2105
            { 0, 204, 306, 404, 504, 602, 701, 731, 829, 927, 1027, 1125, 1225 }, // 2106
            { 4, 124, 223, 325, 423, 523, 621, 720, 819, 917, 1016, 1115, 1214, 1313 }, // 2107
            { 0, 212, 313, 411, 511, 609, 709, 807, 906, 1005, 1103, 1203, 1301 }, // 2108
            { 9, 131, 302, 331, 430, 530, 628, 728, 826, 925, 1024, 1122, 1222, 1320 }, // 2109
            { 0, 219, 320, 419, 519, 617, 717, 816, 914, 1014, 1112, 1211, 1310 }, // 2110
            { 0, 208, 310, 408, 508, 606, 706, 805, 903, 1003, 1101, 1201, 1231 }, // 2111
            { 6, 129, 227, 328, 426, 526, 624, 724, 822, 921, 1021, 1119, 1219, 1318 }, // 2112
            { 0, 216, 318, 416, 515, 614, 713, 811, 910, 1010, 1108, 1208, 1307 }, // 2113
            { 0, 206, 307, 406, 505, 603, 703, 801, 830, 929, 1028, 1127, 1227 }, // 2114
            { 4, 126, 225, 326, 425, 524, 622, 722, 820, 918, 1018, 1116, 1216, 1315 }, // 2115
            { 0, 214, 314, 413, 512, 611, 710, 809, 907, 1006, 1105, 1204, 1303 }, // 2116
            { 0, 202, 303, 402, 502, 531, 630, 729, 828, 926, 1025, 1124, 1223 }, // 2117
            { 3, 122, 220, 322, 421, 520, 619, 719, 817, 916, 1015, 1113, 1213, 1311 }, // 2118
            { 0, 210, 311, 410, 509, 608, 708, 806, 905, 1004, 1103, 1202, 1301 }, // 2119
            { 7, 130, 229, 329, 428, 527, 626, 725, 824, 923, 1022, 1121, 1220, 1319 }, // 2120
            { 0, 217, 319, 417, 517, 615, 715, 813, 912, 1011, 1110, 1210, 1309 }, // 2121
            { 0, 207, 308, 407, 506, 605, 704, 802, 901, 930, 1030, 1129, 1229 }, // 2122
            { 5, 127, 226, 328, 426, 525, 624, 723, 821, 920, 1019, 1118, 1218, 1317 }, // 2123
            { 0, 215, 316, 414, 514, 612, 712, 810, 908, 1008, 1106, 1206, 1305 }, // 2124
            { 0, 203, 305, 404, 503, 602, 701, 731, 829, 927, 1027, 1125, 1225 }, // 2125
            { 4, 123, 222, 324, 423, 522, 621, 720, 819, 917, 1016, 1115, 1214, 1313 }, // 2126
            { 0, 211, 313, 412, 511, 610, 710, 808, 906, 1006, 1104, 1204, 1302 }, // 2127
            { 11, 201, 301, 331, 429, 529, 628, 727, 826, 924, 1024, 1122, 1222, 1320 }, // 2128
            { 0, 219, 320, 419, 518, 617, 716, 815, 914, 1013, 1112, 1211, 1310 }, // 2129
            { 0, 208, 310, 408, 508, 606, 705, 804, 903, 1002, 1101, 1201, 1230 }, // 2130
            { 6, 129, 227, 329, 427, 527, 625, 724, 823, 921, 1021, 1120, 1220, 1318 }, // 2131
            { 0, 217, 317, 416, 515, 614, 713, 811, 910, 1009, 1108, 1208, 1306 }, // 2132
            { 0, 205, 307, 405, 505, 603, 703, 801, 830, 928, 1028, 1127, 1226 }, // 2133
            { 5, 125, 224, 326, 424, 524, 622, 722, 820, 918, 1018, 1116, 1216, 1314 }, // 2134
            { 0, 213, 315, 413, 513, 612, 711, 809, 908, 1007, 1106, 1205, 1304 }, // 2135
            { 0, 202, 303, 401, 501, 531, 629, 729, 827, 926, 1025, 1124, 1223 }, // 2136
            { 2, 122, 220, 322, 420, 520, 618, 718, 817, 915, 1015, 1113, 1213, 1311 }, // 2137
            { 0, 210, 311, 410, 509, 608, 707, 806, 904, 1004, 1103, 1202, 1301 }, // 2138
            { 7, 130, 301, 330, 429, 528, 626, 726, 824, 923, 1023, 1122, 1221, 1320 }, // 2139
            { 0, 218, 319, 417, 517, 615, 714, 813, 911, 1011, 1110, 1209, 1308 }, // 2140
            { 0, 207, 308, 407, 506, 605, 704, 802, 901, 930, 1030, 1128, 1228 }, // 2141
            { 5, 127, 226, 327, 426, 525, 624, 723, 821, 919, 1019, 1117, 1217, 1316 }, // 2142
            { 0, 215, 316, 415, 515, 613, 713, 811, 909, 1008, 1107, 1206, 1305 }, // 2143
            { 0, 204, 304, 403, 503, 602, 701, 730, 829, 927, 1027, 1125, 1224 }, // 2144
            { 4, 123, 222, 323, 422, 522, 620, 720, 818, 917, 1016, 1115, 1214, 1313 }, // 2145
            { 0, 211, 312, 411, 511, 609, 709, 807, 906, 1006, 1104, 1204, 1302 }, // 2146
            { 0, 201, 302, 331, 430, 529, 628, 728, 826, 925, 1025, 1123, 1223 }, // 2147
            { 1, 121, 220, 320, 419, 518, 616, 716, 814, 913, 1013, 1111, 1211, 1310 }, // 2148
            { 0, 208, 310, 408, 508, 606, 705, 804, 902, 1002, 1031, 1130, 1230 }, // 2149
            { 6, 129, 227, 329, 427, 527, 625, 724, 822, 921, 1020, 1119, 1219, 1318 }, // 2150

    };

    private int year;

    private boolean leap;

    private int month;

    private int day;

    /**
     * 二分查找
     * 
     * @param values
     * @param low
     * @param high
     * @param random
     * @return
     */
    private static int binarySearch(int[] values, int low, int high, int random) {
        while (low < high) {
            if (random < values[low]) {
                return low;
            }
            if (random >= values[high - 1]) {
                return high;
            }
            int middle = (low + high) / 2;
            if (random < values[middle]) {
                high = middle;
            } else {
                low = middle;
            }
        }
        return -1;
    }

    public LunarDate(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int[] values = lunar2Solar[year - MINIMUM_YEAR];
        int index = binarySearch(values, 0, values.length, month * 100 + day) - 1;
        if (index == 0) {
            year = year - 1;
            month = month + 12;
            values = lunar2Solar[year - MINIMUM_YEAR];
            index = binarySearch(values, 0, values.length, month * 100 + day) - 1;
        }
        // 最接近的农历月份
        int value = values[index];
        LocalDate from = new SolarDate(year, value / 100, value % 100).getDate();
        LocalDate to = date;
        this.year = year;
        int leap = values[0];
        if (leap > 0) {
            this.leap = leap + 1 == index;
            if (index > leap) {
                this.month = index - 1;
            } else {
                this.month = index;
            }
        } else {
            this.leap = false;
            this.month = index;
        }
        this.day = (int) ChronoUnit.DAYS.between(from, to) + 1;
    }

    public LunarDate(int year, boolean leap, int month, int day) {
        this.year = year;
        this.leap = leap;
        this.month = month;
        // 检查是否存在指定的天数
        assert day <= getDaySize(year, leap, month);
        this.day = day;
    }

    /**
     * 获取阴历年
     * 
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * 获取阴历月
     * 
     * @return
     */
    public int getMonth() {
        return month;
    }

    /**
     * 获取阴历日
     * 
     * @return
     */
    public int getDay() {
        return day;
    }

    /**
     * 是否闰月
     * 
     * @return
     */
    public boolean isLeap() {
        return leap;
    }

    /**
     * 阴历转阳历
     * 
     * @return
     */
    public SolarDate getSolar() {
        int year = this.year;
        int month = this.month;
        int day = this.day;
        int leap = lunar2Solar[year - MINIMUM_YEAR][0];
        int value = lunar2Solar[year - MINIMUM_YEAR][leap > 0 && (this.leap || month > leap) ? month + 1 : month];
        SolarDate date = new SolarDate(year, value / 100, value % 100);
        date = new SolarDate(date.getDate().plusDays(day - 1));
        return date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        LunarDate that = (LunarDate) object;
        if (this.day != that.day)
            return false;
        if (this.leap != that.leap)
            return false;
        if (this.month != that.month)
            return false;
        if (this.year != that.year)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int hash = 31;
        int result = 1;
        result = hash * result + day;
        result = hash * result + (leap ? 1231 : 1237);
        result = hash * result + month;
        result = hash * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "LunarDate [year=" + year + ", leap=" + leap + ", month=" + month + ", day=" + day + "]";
    }

    /**
     * 获取指定年份的闰月
     * 
     * @param year
     * @return
     */
    public static int getLeapMonth(int year) {
        int[] values = lunar2Solar[year - MINIMUM_YEAR];
        return values[0];
    }

    /**
     * 获取指定月份的天数
     * 
     * @param year
     * @param leap
     * @param month
     * @return
     */
    public static int getDaySize(int year, boolean leap, int month) {
        // 检查是否存在指定的天数
        int[] values = lunar2Solar[year - MINIMUM_YEAR];
        if (leap) {
            if (values[0] != month) {
                throw new IllegalArgumentException();
            }
        }
        int index = leap || (values[0] != 0 && month > values[0]) ? month + 1 : month;
        int fromValue = values[index];
        LocalDate from = new SolarDate(year, fromValue / 100, fromValue % 100).getDate();
        if (index >= values.length - 1) {
            year = year + 1;
            index = 1;
            values = lunar2Solar[year - MINIMUM_YEAR];
        } else {
            index++;
        }
        int toValue = values[index];
        LocalDate to = new SolarDate(year, toValue / 100, toValue % 100).getDate();
        return (int) ChronoUnit.DAYS.between(from, to);
    }

}
