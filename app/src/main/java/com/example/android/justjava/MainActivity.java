package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int noOfCof = 2;

    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public int calcprice(int qty, boolean c1, boolean c2) {
        int price =qty * 10;
        if (c1 == true) {
            price = price + (qty * 3);
        }
        if (c2 == true) {
            price = price + (qty * 2);
        }

        return price;
    }

    public String orderSummary(int qty) {
        String pricemsg = ("\nQuantity:" + qty + " cups");
        return pricemsg;
    }


    public void submitOrder(View view) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.whippedcream_checkbox_id);
        boolean c1 = checkBox.isChecked();
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.choclate_checkbox_id);
        boolean c2 = checkBox2.isChecked();
        EditText e1 = (EditText) findViewById(R.id.id_for_name);
        String name = e1.getText().toString();
        calcprice(noOfCof, c1, c2);
        String order_summary_message=("Thank you!!\n\nOrder Summary\n" + "Name:" + name + orderSummary(noOfCof) + "\nWhipped Cream:" + c1 + "\nChoclate:" + c2 + "\nYour bill :$ " + (calcprice(noOfCof, c1, c2)));


        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Recipt for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,order_summary_message);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }


        displayMessage(order_summary_message);
    }

    public void decrement(View view) {
        if (noOfCof >= 1) {
            noOfCof--;
            display(noOfCof);
        } else
            display(0);
    }

    public void increment(View view) {
        noOfCof++;
        display(noOfCof);
    }


}
