package in.canews.zeronet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class MainActivity extends AppCompatActivity {
    protected Python py = Python.getInstance();
    private PyObject sys;
    private PyObject stdin, stdout, stderr;
    private PyObject realStdin, realStdout, realStderr;
    public static final int STDIN_DISABLED = 0x0, STDIN_ENABLED = 0x1;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tv = findViewById(R.id.textview);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        callPython();
    }

    private void callPython() {
//        try {
//            InputStream f = getApplication().getAssets().open("chaquopy/app.zip");
//            ZipInputStream zin = new ZipInputStream(f);
//            ZipEntry ze = null;
//            while ((ze = zin.getNextEntry()) != null) {
//                Log.v("Decompress", "Unzipping " + ze.getName());
//
//                if(ze.isDirectory()) {
//                    dirChecker(ze.getName());
//                } else {
//                    FileOutputStream fout = new FileOutputStream(getFilesDir() + ze.getName());
//                    for (int c = zin.read(); c != -1; c = zin.read()) {
//                        fout.write(c);
//                    }
//
//                    zin.closeEntry();
//                    fout.close();
//                }
//
//            }
//            zin.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        PyObject obj = py.getModule("zeronet.zeronet").callAttr("start");

    }
    private void dirChecker(String dir) {
        File f = new File(getFilesDir() + dir);

        if(!f.isDirectory()) {
            f.mkdirs();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
