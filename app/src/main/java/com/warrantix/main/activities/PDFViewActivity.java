package com.warrantix.main.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.joanzapata.pdfview.PDFView;
import com.warrantix.main.R;

public class PDFViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        initialize();
    }

    private void initialize() {
        String pdfPath = getIntent().getStringExtra("path");

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(pdfPath.isEmpty() ? "temp.pdf" : pdfPath);

        ImageButton backButton = (ImageButton) findViewById(R.id.brand_arrow);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        PDFView pdfView = (PDFView) findViewById(R.id.pdfview);
        pdfView.fromAsset(pdfPath.isEmpty() ? "temp.pdf" : pdfPath)
                .defaultPage(1)
                .swipeVertical(true)
//                .showMinimap(false)
//                .enableSwipe(true)
//                .onDraw(onDrawListener)
//                .onLoad(onLoadCompleteListener)
//                .onPageChange(onPageChangeListener)
                .load();
    }

}