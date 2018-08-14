package com.TeamAA.Beer_Journal;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sean.beckstead
 * Date: 11/6/13
 * Time: 8:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class EntryPage extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildXmlLayout();




    }

    private void buildXmlLayout() {
        //To change body of created methods use File | Settings | File Templates.
        try {
            String xmlProps = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "\n" +
                    "<RelativeLayout\n" +
                    "        android:layout_width=\"fill_parent\"\n" +
                    "        android:layout_height=\"fill_parent\" xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                    "    <ImageView\n" +
                    "            android:layout_width=\"103dp\"\n" +
                    "            android:layout_height=\"60dp\"\n" +
                    "            android:id=\"@+id/imageView\"\n" +
                    "            android:layout_alignParentLeft=\"true\" android:layout_marginLeft=\"13dp\" android:layout_alignParentTop=\"true\"\n" +
                    "            android:layout_marginTop=\"9dp\" android:src=\"#00f0ff\"/>\n" +
                    "    <Button\n" +
                    "            android:layout_width=\"wrap_content\"\n" +
                    "            android:layout_height=\"wrap_content\"\n" +
                    "            android:text=\"Submit\"\n" +
                    "            android:id=\"@+id/submitBtn\" android:layout_alignParentRight=\"true\" android:layout_alignParentBottom=\"true\"/>\n" +
                    "    <EditText\n" +
                    "            android:layout_width=\"114dp\"\n" +
                    "            android:layout_height=\"wrap_content\"\n" +
                    "            android:id=\"@+id/editText\"\n" +
                    "            android:layout_alignParentLeft=\"true\" android:layout_marginLeft=\"215dp\" android:layout_alignParentTop=\"true\"\n" +
                    "            android:layout_marginTop=\"22dp\"/>\n" +
                    "    <TextView\n" +
                    "            android:layout_width=\"wrap_content\"\n" +
                    "            android:layout_height=\"wrap_content\"\n" +
                    "            android:text=\"Name\"\n" +
                    "            android:id=\"@+id/textView\"\n" +
                    "            android:phoneNumber=\"false\"\n" +
                    "            android:layout_alignParentLeft=\"true\" android:layout_marginLeft=\"172dp\"\n" +
                    "            android:layout_alignBaseline=\"@+id/editText\"/>\n" +
                    "    <RatingBar\n" +
                    "            android:layout_width=\"wrap_content\"\n" +
                    "            android:layout_height=\"30dp\"\n" +
                    "            android:id=\"@+id/ratingBar\"\n" +
                    "            android:numStars=\"5\" android:layout_below=\"@+id/editText\" android:layout_centerHorizontal=\"true\"/>\n" +
                    "    <ScrollView\n" +
                    "            android:layout_width=\"wrap_content\"\n" +
                    "            android:layout_height=\"132dp\"\n" +
                    "            android:id=\"@+id/scrollView\"\n" +
                    "            android:layout_alignRight=\"@+id/submitBtn\" android:layout_alignParentTop=\"true\"\n" +
                    "            android:layout_marginTop=\"116dp\" android:layout_above=\"@+id/submitBtn\" android:layout_alignParentLeft=\"true\">\n" +
                    "        <RelativeLayout\n" +
                    "                android:layout_width=\"fill_parent\"\n" +
                    "                android:layout_height=\"187dp\" android:layout_alignParentLeft=\"true\"\n" +
                    "                android:layout_alignParentTop=\"true\" android:layout_marginTop=\"97dp\" android:layout_above=\"@+id/submitBtn\">";

            String type;
            String prevName = "";
            XmlPullParser xpp=getResources().getXml(R.xml.property_definitions);

            while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType()==XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("property")) {
                        type = (xpp.getAttributeValue(1));
                        if (type.contains("selectList")) {
                            xmlProps += "<Spinner\n" +
                                    "android:layout_width=\"wrap_content\"\n" +
                                    "android:layout_height=\"wrap_content\"\n" +
                                    "android:id=\"@+id/" + xpp.getAttributeValue(0) + "\"\n" +
                                    "android:layout_centerHorizontal=\"true\" ";
                            if (!prevName.isEmpty()) {
                                xmlProps += "android:layout_below=\"@+id/" + prevName + "/>\"\n";
                            } else {
                                xmlProps += "/>\"\n";
                            }
                        } else if (type.contains("intRange")) {
                            xmlProps += "<SeekBar\n" +
                                    "android:layout_width=\"211dp\"\n" +
                                    "android:layout_height=\"wrap_content\"\n" +
                                    "android:id=\"@+id/" + xpp.getAttributeValue(0) + "\"\n" +
                                    "android:layout_centerHorizontal=\"true\" android:layout_alignParentTop=\"true\"";
                            if (!prevName.isEmpty()) {
                                xmlProps += "android:layout_below=\"@+id/" + prevName + "/>\"\n";
                            } else {
                                xmlProps += "/>\"\n";
                            }
                        prevName = xpp.getAttributeValue(0);
                        }
                    }
                }

                xpp.next();
            }
            xmlProps += "</RelativeLayout>\n" +
                    "    </ScrollView>\n" +
                    "</RelativeLayout>";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d1 = builder.parse(new InputSource(new StringReader(xmlProps)));

            setContentView(R.layout.entrylayout);

            LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_layout_id);
            View view = getLayoutInflater().inflate(d1);

            mainLayout.addView(view);

        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("file not created");
        }

    }
}
