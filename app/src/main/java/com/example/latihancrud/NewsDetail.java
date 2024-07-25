package com.example.latihancrud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewsDetail extends AppCompatActivity {
    TextView newsTitle, newsSubtitle;
    ImageView newsImage;
    Button edit, delete;
    private FirebaseFirestore db;
    private static final String TAG = "NewsDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // Initialize UI components
        newsTitle = findViewById(R.id.newsTitle);
        newsSubtitle = findViewById(R.id.newsSubtitle);
        newsImage = findViewById(R.id.newsImage);
        edit = findViewById(R.id.editButton);
        delete = findViewById(R.id.deleteButton);
        db = FirebaseFirestore.getInstance();

        // Get data from Intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String subtitle = intent.getStringExtra("desc");
        String imageUrl = intent.getStringExtra("imageUrl");

        // Log the received ID and other details for debugging
        Log.d(TAG, "Received ID: " + id);
        Log.d(TAG, "Received Title: " + title);
        Log.d(TAG, "Received Subtitle: " + subtitle);
        Log.d(TAG, "Received Image URL: " + imageUrl);

        // Set data to UI components
        newsTitle.setText(title);
        newsSubtitle.setText(subtitle);
        Glide.with(this).load(imageUrl).into(newsImage);

        // Edit button click listener
        edit.setOnClickListener(v -> {
            Intent editIntent = new Intent(NewsDetail.this, NewsAdd.class);
            editIntent.putExtra("id", id);
            editIntent.putExtra("title", title);
            editIntent.putExtra("desc", subtitle);
            editIntent.putExtra("imageUrl", imageUrl);
            startActivity(editIntent);
        });

        // Delete button click listener
        delete.setOnClickListener(v -> {
            if (id != null && !id.isEmpty()) {
                db.collection("news").document(id)
                        .delete()
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(NewsDetail.this, "News deleted successfully", Toast.LENGTH_SHORT).show();
                            // Navigate back to MainActivity
                            Intent mainIntent = new Intent(NewsDetail.this, MainActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(mainIntent);
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(NewsDetail.this, "Error deleting news: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "Error deleting document", e);
                        });
            } else {
                Toast.makeText(NewsDetail.this, "Invalid document ID", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "Invalid document ID: " + id);
            }
        });
    }
}
