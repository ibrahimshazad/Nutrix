package com.example.nutrix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class CartActivity extends AppCompatActivity {
    RecyclerView recycleCart;
    Button NextProcessButton;
    TextView TotalPrice;
    Context context;
    static Cart[] cart2;
    private FirebaseAuth auth;
//fix
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recycleCart = findViewById(R.id.recyclecart);
        recycleCart.setHasFixedSize(true);
        recycleCart.setLayoutManager(new LinearLayoutManager(this));
        context = getApplicationContext();
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Shopping Cart");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);;
        }
       BottomNavigationView bottomNavigationView = findViewById(R.id.navi_menu);
        bottomNavigationView.setSelectedItemId(R.id.menu_shoppingcart);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.menu_mealplan:
                        startActivity(new Intent(getApplicationContext(),MealPlanActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_shoppingcart:
                        return true;
                    case R.id.menu_account:
                        startActivity(new Intent(getApplicationContext(), ActivityAccount.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        auth = FirebaseAuth.getInstance();
        Intent intent1 = getIntent();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        Query finduser = ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("cart");

        finduser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                long cs = dataSnapshot.getChildrenCount();
                int sc = (int)cs;
                cart2 = new Cart[sc];
                int total1 = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String itemname;
                    int itemprice,itemq,total;
                    String reference = snapshot.getKey();
                    itemname = dataSnapshot.child(reference).child("name").getValue().toString();
                   itemprice = Integer.valueOf(dataSnapshot.child(reference).child("price").getValue().toString());
                    itemq =  Integer.valueOf(dataSnapshot.child(reference).child("quantity").getValue().toString());
                    total = itemprice*itemq;
                    cart2[i] = new Cart(itemname,itemprice,itemq,total);
                    total1 +=total;
                    i+=1;
                }
                ItemBrowserAdapter itemadapter = new ItemBrowserAdapter(cart2,CartActivity.this);
                recycleCart.setAdapter(itemadapter);
                TextView button = (TextView) findViewById(R.id.totalprice);
                button.setText("Total Price = $"+String.valueOf(total1));

                TextView textview = (TextView) findViewById(R.id.NextProcessButton);
                textview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity((new Intent(getApplicationContext(),ConfirmFinalOrderActivity.class)));
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }


}