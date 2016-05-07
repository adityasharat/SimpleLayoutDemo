package com.jsvoid.simpledemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

public class SimpleHttpActivity extends AppCompatActivity {

    private EditText mEditTextImageUrl;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_http);

        mEditTextImageUrl = (EditText) findViewById(R.id.image_url);
        mImageView = (ImageView) findViewById(R.id.image);

        View vButtonLoadImage = findViewById(R.id.load_image);

        if (vButtonLoadImage != null) {
            vButtonLoadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEditTextImageUrl != null) {

                        String link = mEditTextImageUrl.getText().toString();
                        if (!"".equals(link)) {
                            loadImage(link);
                        }
                    }
                }
            });
        }
    }

    private void loadImage(final String link) {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                try {
                    URL url = new URL(link);
                    Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    return image;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                setImage(bitmap);
            }
        }.execute();
    }

    private void setImage(Bitmap image) {
        if (mImageView != null) {
            mImageView.setImageBitmap(image);
        }
    }
}
