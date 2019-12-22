package com.edu_netcracker.academ_resourse.schedule.utils;

public interface ISheduleHTMLConstants {
    String ITMO_TABLE_BEFORE = "border=\"0\"";
    String ITMO_TABLE_AFTER = "border=\"1\"";
    String ITMO_QUERY = "td.lesson dl dd";
    String ITMO_SPLIT = " \\(";
    String SMTU_REGEX = "<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\" width=\"100%\">";
    String SMTU_QUERY = "tbody tr";
    String SMTU_SPLIT1 = "<td>";
    String SMTU_SPLIT2 = "<br>";
    String NSU_TABLE = " border=\"1\"> \n";
    String NSU_QUERY = "div.subject";
    String END_TABLE = "</table>";
    String END_LINE = "\n";
    String SMTU = "SMTU";
    String ITMO = "ITMO";
    String NSU = "NSU";

}
