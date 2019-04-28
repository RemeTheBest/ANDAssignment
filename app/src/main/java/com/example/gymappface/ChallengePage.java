package com.example.gymappface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChallengePage extends AppCompatActivity implements ChallengeAdapter.OnClickListener
{
    RecyclerView challengeList;
    RecyclerView.Adapter mChallengeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ArrayList<Challenges> challenges;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_page);

        challengeList = findViewById(R.id.rv);
        challengeList.hasFixedSize();
        challengeList.setLayoutManager(new LinearLayoutManager(this));

        challenges = new ArrayList<>();

        challenges.add(new Challenges("Aim for the moon","Reach the moon in the pullup game",R.drawable.astronaut));
        challenges.add(new Challenges("... if you miss, hit stars","Reach the star layer in the pullup game",R.drawable.moon));
        challenges.add(new Challenges("Sharp teeth, sharper skills","Don't get eaten by the crocodiles for 10 minutes on the rowmachine",R.drawable.sharp));
        challenges.add(new Challenges("Crocodile Don't-dee","Outsail 50 crocodiles on the rowmachine", R.drawable.crocodile));
        challenges.add(new Challenges("Hill? What Hill?","Climb 10 hills on the Tour De France bike", R.drawable.hill));
        challenges.add(new Challenges("Look at me, I'm a winner","Outrace all bikers on the Tour De France bike", R.drawable.winner));
        challenges.add(new Challenges("All upper arm","Do 100 press-ups in a day", R.drawable.arm));
        challenges.add(new Challenges("Marathon Master","Run 42 km in one go", R.drawable.running));

        mChallengeAdapter = new ChallengeAdapter(challenges,this);
        challengeList.setAdapter(mChallengeAdapter);


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        int challengeNumber = clickedItemIndex + 1;
        Toast t =Toast.makeText(this, "Challenge chosen" + challengeNumber, Toast.LENGTH_SHORT);

        t.show();
    }
}
