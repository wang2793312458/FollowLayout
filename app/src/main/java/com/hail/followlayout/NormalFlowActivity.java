package com.hail.followlayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.AutoFlowLayout;

public class NormalFlowActivity extends AppCompatActivity implements View.OnClickListener {
    private AutoFlowLayout mFlowLayout;
    private String[] mData = {"Java", "C++", "Python", "JavaScript", "Ruby", "Swift", "MATLAB", "Scratch", "Drat", "ABAP", "COBOL", "Fortran", "Scala", "Lisp",
            "Kotlin", "Erlang", "Groovy", "Scheme", "Rust", "Logo", "Prolog", "LabVIEW"};
    private LayoutInflater mLayoutInflater;
    private Button mSingleButton;
    private Button mMultiLineButton;
    private Button mAddButton;
    private Button mDeleteButton;
    private Button mMultiSelectedButton;
    private Button mCenterButton;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_flow);
        mFlowLayout = (AutoFlowLayout) findViewById(R.id.afl_cotent);
        mLayoutInflater = LayoutInflater.from(this);
        mSingleButton = (Button) findViewById(R.id.bt_single);
        mMultiLineButton = (Button) findViewById(R.id.bt_multi);
        mAddButton = (Button) findViewById(R.id.bt_add);
        mDeleteButton = (Button) findViewById(R.id.bt_delete);
        mMultiSelectedButton = (Button) findViewById(R.id.bt_checked);
        mCenterButton = (Button) findViewById(R.id.bt_center);
        mSingleButton.setOnClickListener(this);
        mMultiLineButton.setOnClickListener(this);
        mAddButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
        mMultiSelectedButton.setOnClickListener(this);
        mCenterButton.setOnClickListener(this);
        for (int i = 0; i < 10; i++) {
            View item = mLayoutInflater.inflate(R.layout.sub_item, null);
            TextView tvAttrTag = (TextView) item.findViewById(R.id.tv_attr_tag);
            tvAttrTag.setText(mData[i]);
            mFlowLayout.addView(item);
        }
        mFlowLayout.setOnItemClickListener(new AutoFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(NormalFlowActivity.this, mData[position], Toast.LENGTH_SHORT).show();
            }
        });
        mFlowLayout.setOnLongItemClickListener(new AutoFlowLayout.OnLongItemClickListener() {
            @Override
            public void onLongItemClick(final int position, View view) {
                AlertDialog dialog = new AlertDialog.Builder(NormalFlowActivity.this)
                        .setTitle("long click")
                        .setMessage("You will delete this tag!")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mFlowLayout.setLineCenter(false);
                                mFlowLayout.deleteView(position);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_single: {
                mFlowLayout.setLineCenter(false);
                mFlowLayout.setSingleLine(true);
                mFlowLayout.setMaxLines(1);
                break;
            }
            case R.id.bt_multi: {
                mFlowLayout.setLineCenter(false);
                mFlowLayout.setSingleLine(false);
                mFlowLayout.setMaxLines(2);
                break;
            }
            case R.id.bt_add: {
                mFlowLayout.setLineCenter(false);
                if (count >= mData.length) {
                    return;
                }
                View item = mLayoutInflater.inflate(R.layout.sub_item, null);
                TextView tvAttrTag = (TextView) item.findViewById(R.id.tv_attr_tag);
                tvAttrTag.setText(mData[count]);
                mFlowLayout.setSingleLine(false);
                mFlowLayout.setMaxLines(Integer.MAX_VALUE);
                mFlowLayout.addView(item);
                count++;
                break;
            }
            case R.id.bt_delete: {
                mFlowLayout.setLineCenter(false);
                mFlowLayout.deleteView();
                break;
            }

            case R.id.bt_checked: {
                mFlowLayout.setLineCenter(false);
                mFlowLayout.setMultiChecked(true);
                break;
            }
            case R.id.bt_center: {
                mFlowLayout.setLineCenter(true);
                break;
            }
        }
    }
}
