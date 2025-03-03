package com.example.internal_files;

import static android.media.MediaCodec.MetricsConstants.MODE;

import static java.lang.reflect.Modifier.PRIVATE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * @author adir amar
 * @version 1
 * @since 24/2/25
 *
 * Each click on the Save button will save and append the text that was typed to the text
 * previously in the file
 * Each click on the Reset button will erase the history of all text typed in the file
 * Each click on the Exit button will react like the Save button and also end the program!
 * Each time the program is run, all the text that was saved on the last exit will be read from
 * the file and displayed in the appropriate field
 * */
public class MainActivity extends AppCompatActivity {
    TextView sum ;
    EditText eT ;
    Button save_btn , reset_btn , exit_btn ;
    String strwr = "" ;

    String text = "" ;
    private final String FILENAME = "inttest.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save_btn =findViewById(R.id.save_btn);
        reset_btn = findViewById(R.id.reset_btn);
        exit_btn = findViewById(R.id.exit_btn);
        eT = findViewById(R.id.eT);
        sum = findViewById(R.id.sum);

        text = gettext();
        sum.setText(text);

    }

    /**
     * Each click on the Save button will save and
     * append the text that was typed to the text previously in the file
     *
     * @param view The Button
     */
    public void clikced2(View view )
    {
    try
    {
        FileOutputStream fOS = openFileOutput(FILENAME, MODE_APPEND);
        OutputStreamWriter oSW = new OutputStreamWriter(fOS);
        BufferedWriter bW = new BufferedWriter(oSW);
        strwr = eT.getText().toString();
        bW.write( strwr);
        bW.close();
        oSW.close();
        fOS.close();
        text = gettext();
        sum.setText(text);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
    /**
     * Each click on the Reset button will erase the history of all text typed in the file
     *
     * @param view The Button
     */

    public void clicked4(View view)
    {
        try
        {
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            bW.write("");
            bW.close();
            oSW.close();
            fOS.close();
            sum.setText("");
            eT.setText("");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
/**
 * Each click on the Exit button will react like the Save button and also end the program!
 *
 * @param view The Button
 */
    }
    public void clikced5(View view)
    {
        clikced2(view);
        finish();

    }

    /**
     * return the text 
     *
     * @return text
     */
    public String gettext() {
        try {
            FileInputStream fIS = openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null)
            {
                sB.append(line + '\n');
                line = bR.readLine();
            }

            bR.close();
            iSR.close();
            fIS.close();
            sum.setText(sB.toString());
            return sB.toString();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * create the options menu
     *
     * @param menu The options menu
     * @return return true
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Checks the selection in the options menu
     *
     * @param menu The selected menu item.
     * @return return true
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String num1 = menu.getTitle().toString();
        if (num1.equals("credits"))
        {
            Intent si = new Intent(this,Page2.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(menu);
    }
}
