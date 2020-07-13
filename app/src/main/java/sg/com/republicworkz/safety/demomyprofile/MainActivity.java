package sg.com.republicworkz.safety.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    protected void save() {
        Toast.makeText(MainActivity.this,"Saving",Toast.LENGTH_LONG).show();

        //Step 1a: Get the user input from the ditText and store it in a variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int intGenderId = rgGender.getCheckedRadioButtonId();

        //Step 1b: Obtain an instance of the SharePreference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1c: Obtain an instance of the SharePreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //Step 1d: Add the key-value pair
        prefEdit.putString("name",strName);
        prefEdit.putFloat("gpa",gpa);
        prefEdit.putInt("genderId",intGenderId);

        //Step 1e: Call commit() to save the changes into SharedPreferences
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharePreference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data from the SharePreferences object
        String strName = prefs.getString("name","Jonn");
        float gpa = prefs.getFloat("gpa",0);
        int intGenderId = prefs.getInt("genderId",R.id.radioButtonMale);

        //Step 2c: Update the UI element with the value
        etName.setText(strName);
        etGPA.setText(gpa+"");
        rgGender.check(intGenderId);
        //rgGender.check(R.id.radioButtonMale);
        //rgGender.check(R.id.radioButtonFemale);
    }
}
