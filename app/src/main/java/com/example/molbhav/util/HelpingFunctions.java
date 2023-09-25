package com.example.molbhav.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;


import com.example.molbhav.LoginActivity;
import com.example.molbhav.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class HelpingFunctions {


//    public void setConstraintLayoutForTraching(ConstraintLayout constraintLayout){
//
//        // Get the ConstraintLayout from your XML layout
////        ConstraintLayout constraintLayout = root.findViewById(R.id.myConstraintLayout);
//
//        // Define the number of times to add the structure
//        int numberOfStructures = 10;
//        int TopId = -1;
//        int BottomId = -1;
//        for (int i = 0; i < numberOfStructures; i++) {
//            // Create ImageView 1 (step1)
//            ImageView step1 = createImageView(R.drawable.correct);
//            step1.setId(View.generateViewId()); // Generate a unique ID
//
//            // Create TextView for step1Status
//            TextView step1Status = createTextView("Complaint Raised");
//            step1Status.setId(View.generateViewId()); // Generate a unique ID
//
//            // Create View (step1View)
//            View step1View = createView(Color.parseColor("#5cb85c")); //Green
//            step1View.setId(View.generateViewId()); // Generate a unique ID
//
////                // Create ImageView 2 (step2)
////                ImageView step2 = createImageView(R.drawable.correct);
////                step2.setId(View.generateViewId()); // Generate a unique ID
//
//            // Add child views to ConstraintLayout
//            constraintLayout.addView(step1);
//            constraintLayout.addView(step1Status);
//            constraintLayout.addView(step1View);
////                constraintLayout.addView(step2);
//
//            // Create ConstraintSet for ConstraintLayout
//            ConstraintSet constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
//
//            // Set constraints for the child views
//            // Constraint 1: Connect the top of step1 to the top of parent
//            if (TopId != -1){
//                constraintSet.connect(step1.getId(), ConstraintSet.TOP, TopId, ConstraintSet.BOTTOM);
//            } else{
//                constraintSet.connect(step1.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
//            }
//
//            // Constraint 2: Connect the start of step1 to the start of parent
//            constraintSet.connect(step1.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
//
//            // Constraint 3: Connect the top of step1Status to the top of step1
//            constraintSet.connect(step1Status.getId(), ConstraintSet.TOP, step1.getId(), ConstraintSet.TOP);
//            constraintSet.connect(step1Status.getId(), ConstraintSet.BOTTOM, step1.getId(), ConstraintSet.BOTTOM);
//            // Constraint 4: Connect the start of step1Status to the end of step1
//            constraintSet.connect(step1Status.getId(), ConstraintSet.START, step1.getId(), ConstraintSet.END,10);
//
//            // Constraint 5: Connect the top of step1View to the bottom of step1
//            constraintSet.connect(step1View.getId(), ConstraintSet.TOP, step1.getId(), ConstraintSet.BOTTOM);
//            // Constraint 6: Connect the bottom of step1View to the top of step2
////                constraintSet.connect(step1View.getId(), ConstraintSet.BOTTOM, step2.getId(), ConstraintSet.TOP);
//            // Constraint 7: Connect the start of step1View to the start of step1
//            constraintSet.connect(step1View.getId(), ConstraintSet.START, step1.getId(), ConstraintSet.START);
//            // Constraint 8: Connect the end of step1View to the end of step1
//            constraintSet.connect(step1View.getId(), ConstraintSet.END, step1.getId(), ConstraintSet.END);
//
////                // Constraint 9: Connect the top of step2 to the bottom of step1View
////                constraintSet.connect(step2.getId(), ConstraintSet.TOP, step1View.getId(), ConstraintSet.BOTTOM);
////                // Constraint 10: Connect the start of step2 to the start of parent
////                constraintSet.connect(step2.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
//
//            // Apply the constraints to the ConstraintLayout
//            constraintSet.applyTo(constraintLayout);
//            TopId = step1View.getId();
//
//        }
//    }
//
//    private ImageView createImageView(int src) {
//        ImageView imageView = new ImageView(requireContext());
//        imageView.setId(View.generateViewId()); // Generate a unique ID
//        imageView.setImageResource(src);
//        imageView.setLayoutParams(new ConstraintLayout.LayoutParams(70, 70));
//        return imageView;
//    }
//
//    private TextView createTextView(String text) {
//        TextView textView = new TextView(requireContext());
//        textView.setId(View.generateViewId()); // Generate a unique ID
//        textView.setText(text);
//        textView.setTextColor(Color.BLACK); // Set text color
//        textView.setLayoutParams(new ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        ));
//        return textView;
//    }
//
//    private View createGuideline() {
//        View guideline = new View(requireContext());
//        guideline.setId(View.generateViewId()); // Generate a unique ID
//        guideline.setBackgroundColor(Color.parseColor("#5cb85c")); // Set guideline color green
//        guideline.setLayoutParams(new ConstraintLayout.LayoutParams(5, 300));
//        return guideline;
//    }
//    // Helper method to create View (e.g., guideline)
//    private View createView(int color) {
//        View view = new View(requireActivity());
//        view.setBackgroundColor(color);
//        view.setLayoutParams(new ConstraintLayout.LayoutParams(5, 300));
//        return view;
//    }

    public static AlertDialog connectionNotFoundAlert(Activity activity,View.OnClickListener onClickListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View layout_dialog = LayoutInflater.from(activity).inflate(R.layout.check_internet_dialog,null);
        builder.setView(layout_dialog);

        AppCompatButton btnRetry = layout_dialog.findViewById(R.id.btnRetry);
        AppCompatButton btnBack = layout_dialog.findViewById(R.id.btnBack);
        AlertDialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.CENTER);

        btnRetry.setOnClickListener(onClickListener);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).popBackStack(R.id.nav_customer_dashboard, false);
//            }
//        });

        return dialog;
    }


    public static class DateTextWatcher implements TextWatcher {

        public static final int MAX_FORMAT_LENGTH = 8;
        public static final int MIN_FORMAT_LENGTH = 3;

        private String updatedText;
        private boolean editing;


        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void onTextChanged(CharSequence text, int start, int before, int count) {
            if (text.toString().equals(updatedText) || editing) return;

//            String digitsOnly = text.toString().replaceAll("\\D", "");
            String digitsOnly = text.toString().replaceAll("-", "");
            int digitLen = digitsOnly.length();

            if (digitLen < MIN_FORMAT_LENGTH || digitLen > MAX_FORMAT_LENGTH) {
                updatedText = digitsOnly;
                return;
            }

            if (digitLen <= 4) {
                String month = digitsOnly.substring(0, 2);
                String day = digitsOnly.substring(2);

                updatedText = String.format(Locale.US, "%s-%s", month, day);
            }
            else {
                String month = digitsOnly.substring(0, 2);
                String day = digitsOnly.substring(2, 4);
                String year = digitsOnly.substring(4);

                updatedText = String.format(Locale.US, "%s-%s-%s", month, day, year);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

            if (editing) return;

            editing = true;

            editable.clear();
            editable.insert(0, updatedText);

            editing = false;
        }
    }

    public static void setLiveErrorNull(AutoCompleteTextView actView){ //for setting error null after textchange in acmltview

        actView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().isEmpty()){
                    actView.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public static void setErrorNull(ViewGroup viewGroup){ //remove required error in textviews

        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                setErrorNull((ViewGroup) view);
            }
            else if ((view instanceof EditText)) {
                EditText edittext = (EditText) view;
                if (!edittext.getText().toString().trim().equals("")) {
                    edittext.setError(null);
                }
            }
            if ((view instanceof AutoCompleteTextView)) {
                AutoCompleteTextView edittext = (AutoCompleteTextView) view;
                if (!edittext.getText().toString().trim().equals("")) {
                    edittext.setError(null);
                }

            }

        }
    }

    public static String dateFormat(String dateString) throws ParseException {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdfNew = new SimpleDateFormat("dd-MM-yyyy");

        Date date = sdf.parse(dateString);

        assert date != null;
        return sdfNew.format(date);
    }

    public static String timeFormat(String dateString) throws ParseException {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdfNew = new SimpleDateFormat("HH:mm");

        Date date = sdf.parse(dateString);

        String formattedDate = sdfNew.format(date);

        return formattedDate;
    }

    public static MaterialAlertDialogBuilder showAlert(String message, Context context) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setMessage(message).setTitle("Alert!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing
                    }
                });

        return builder;
    }

    public static void setupUI(View view,Activity activity) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText) || view instanceof AutoCompleteTextView) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView,activity);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Bitmap compressImagebyUri(Context context, Uri uri) throws IOException {

        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

        ExifInterface exif = HelpingFunctions.getExifInterface(context,uri);

        assert exif != null;
        int orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        Log.d("EXIF", "Exif: " + orientation);
        Matrix matrix = new Matrix();
        if (orientation == 6) {
            matrix.postRotate(90);
            Log.d("EXIF", "Exif: " + orientation);
        } else if (orientation == 3) {
            matrix.postRotate(180);
            Log.d("EXIF", "Exif: " + orientation);
        } else if (orientation == 8) {
            matrix.postRotate(270);
            Log.d("EXIF", "Exif: " + orientation);
        } else if (orientation == ExifInterface.ORIENTATION_UNDEFINED){
            matrix.postRotate(0);
            Log.d("EXIF", "Exif: " + orientation);
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);

        return bitmap;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    public static ExifInterface getExifInterface(Context context, Uri uri) {
        try {
            String path = uri.toString();
            if (path.startsWith("file://")) {
                return new ExifInterface(path);
            }
            if (path.startsWith("content://")) {
                InputStream inputStream = context.getContentResolver().openInputStream(uri);
                return new ExifInterface(inputStream);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap compressImage(String imageUri) {

        String filePath = imageUri;
        // Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap scaledBitmap = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;
        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

        options.inSampleSize = ImageLoadingUtils.calculateInSampleSize(options,
                actualWidth, actualHeight);
        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
            scaledBitmap = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

            try {
                scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight,
                        Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError exception1) {
                exception.printStackTrace();

                float ratioX = actualWidth / (float) options.outWidth;
                float ratioY = actualHeight / (float) options.outHeight;
                float middleX = actualWidth / 2.0f;
                float middleY = actualHeight / 2.0f;

                Matrix scaleMatrix = new Matrix();
                scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

                Canvas canvas = new Canvas(scaledBitmap);
                canvas.setMatrix(scaleMatrix);
                canvas.drawBitmap(scaledBitmap,
                        middleX - scaledBitmap.getWidth() / 2, middleY
                                - scaledBitmap.getHeight() / 2, new Paint(
                                Paint.FILTER_BITMAP_FLAG));

                ExifInterface exif;
                try {
                    exif = new ExifInterface(filePath);

                    int orientation = exif.getAttributeInt(
                            ExifInterface.TAG_ORIENTATION, 0);
                    Log.d("EXIF", "Exif: " + orientation);
                    Matrix matrix = new Matrix();
                    if (orientation == 6) {
                        matrix.postRotate(90);
                        Log.d("EXIF", "Exif: " + orientation);
                    } else if (orientation == 3) {
                        matrix.postRotate(180);
                        Log.d("EXIF", "Exif: " + orientation);
                    } else if (orientation == 8) {
                        matrix.postRotate(270);
                        Log.d("EXIF", "Exif: " + orientation);
                    }
                    scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                            scaledBitmap.getWidth(), scaledBitmap.getHeight(),
                            matrix, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        return scaledBitmap;

    }

    public static Bitmap compress(Bitmap yourBitmap){
        //converted into webp into lowest quality
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        yourBitmap.compress(Bitmap.CompressFormat.WEBP,0,stream);//0=lowest, 100=highest quality
        byte[] byteArray = stream.toByteArray();


        //convert your byteArray into bitmap
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
    }

    public static String getMonthName(String date) throws ParseException {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date PaymentDate = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        assert PaymentDate != null;
        calendar.setTime(PaymentDate);
//            int month = PaymentDate.getMonth();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        Log.d("Month",String.valueOf(month));
        String MonthName = "";
        if (month == 0){
            MonthName = "January";
        }else if (month == 1){
            MonthName = "February";
        }else if (month == 2){
            MonthName = "March";
        }else if (month == 3){
            MonthName = "April";
        }else if (month == 4){
            MonthName = "May";
        }else if (month == 5){
            MonthName = "June";
        }else if (month == 6){
            MonthName = "July";
        }else if (month == 7){
            MonthName = "August";
        }else if (month == 8){
            MonthName = "September";
        }else if (month == 9){
            MonthName = "October";
        }else if (month == 10){
            MonthName = "November";
        }else if (month == 11){
            MonthName = "December";
        }
        return MonthName;
    }

    public static int getYear(String date) throws ParseException {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date PaymentDate = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        assert PaymentDate != null;
        calendar.setTime(PaymentDate);
//            int month = PaymentDate.getMonth();

        return calendar.get(Calendar.YEAR);
    }
}
