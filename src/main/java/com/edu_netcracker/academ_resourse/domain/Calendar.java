package com.edu_netcracker.academ_resourse.domain;

import sun.util.calendar.Gregorian;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Calendar {
    public static final Map<String, String> calendar;

    static
    {
        calendar = new HashMap<>();
        calendar.put("January", "<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">January</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th><th>31</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("February", "<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">February</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n");
        calendar.put("March", "<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">March</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th><th>31</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("April", "<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">April</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("May", " <table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">May</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th><th>31</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("June", "<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">June</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("July", "<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">July</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th><th>31</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("August", " <table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">August</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th><th>31</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("September", "<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">September</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("October", " <table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">October</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th><th>31</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("November", " <table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">November</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
        calendar.put("December", " <table cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n" +
                "       <thead><th colspan=\"7\">December</th>\n" +
                "     </thead>\n" +
                "           <tbody>\n" +
                "             <tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr>\n" +
                "           <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th></tr>\n" +
                "           <tr><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>13</th><th>14</th></tr>\n" +
                "           <tr><th>15</th><th>16</th><th>17</th><th>18</th><th>19</th><th>20</th><th>21</th></tr>\n" +
                "           <tr><th>22</th><th>23</th><th>24</th><th>25</th><th>26</th><th>27</th><th>28</th></tr>\n" +
                "           <tr><th>29</th><th>30</th><th>31</th></tr>\n" +
                "           </tbody>\n" +
                "         </table>");
    }

    public static String getCalendarTable() {
        DateFormat df = new SimpleDateFormat("MMMM");
        char[] charArray = df.format(new Date()).toCharArray();
        charArray[0] = Character.toUpperCase(charArray[0]);
        String month = new String(charArray);

        boolean isLeapYear = new GregorianCalendar().isLeapYear(new Date().getYear());
    if(month.equals("February")) {
        if (isLeapYear)
            return calendar.get(month) + " <tr><th>29</th></tr></tbody>\n" + "</table>";
        else
            return calendar.get(month) + "</tbody>\n" + "</table>";
    }
    else {
        return calendar.get(month);
    }
    }
}
