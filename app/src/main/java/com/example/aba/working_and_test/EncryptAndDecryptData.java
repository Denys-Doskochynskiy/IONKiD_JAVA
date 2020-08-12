package com.example.aba.working_and_test;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aba.R;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptAndDecryptData extends AppCompatActivity {
    private static String AES = "AES";
    EditText inputText, keyText;
    TextView outText;
    Button encBtn, decBtn;
    String outString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_and_decrypt_data);
        findViewAndName();
        /*encBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    outString = encrypt(inputText.getText().toString(), keyText.getText().toString());
                    outText.setText(outString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        decBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    outString=decrypt(outString,keyText.getText().toString());
                    outText.setText(outString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });*/
    }
public  static String decrypt(String outString,String password) throws Exception{
    SecretKeySpec key = generateKey(password);
    Cipher cipher = Cipher.getInstance(AES);
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] decodedValue =Base64.decode(outString,Base64.DEFAULT);
    byte[]decValue=cipher.doFinal(decodedValue);
    String decryptedValue=new String(decValue);
    return decryptedValue;

}
    public static String encrypt(String Data, String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = cipher.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString (encVal, Base64.DEFAULT);
        return encryptedValue;
    }

    private static SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

    public void findViewAndName() {
        inputText = findViewById(R.id.inputText);
        outText = findViewById(R.id.outText);
        encBtn = findViewById(R.id.encBtn);
        decBtn = findViewById(R.id.decBtn);
        keyText = findViewById(R.id.keyText);
    }
}
