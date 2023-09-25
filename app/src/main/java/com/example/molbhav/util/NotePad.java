package com.example.molbhav.util;

public class NotePad {

//    {
//        //Tracking
//
//
//        // Get the ConstraintLayout from your XML layout
//        ConstraintLayout constraintLayout = root.findViewById(R.id.myConstraintLayout);
//
//        // Define the number of times to add the structure
//        int numberOfStructures = 10;
//        int TopId = -1;
//        int BottomId = -1;
//
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
//
//
//
//
//
//    }

}
