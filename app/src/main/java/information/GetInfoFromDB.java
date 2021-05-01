package information;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import information.UserInformation;

public class GetInfoFromDB {

    public GetInfoFromDB(){

    }

    public ArrayList<Map> get_info_from_db(String database){
        final ArrayList<Map> list=new ArrayList<>();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(database);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    UserInformation info=snapshot.getValue(UserInformation.class);

                    Map<String, String> map = new HashMap<String, String>();
                    map.put("username", info.getUsername());
                    map.put("email", info.getEmail());
                    map.put("password", info.getPassword());
                    map.put("avatar_name", info.getAvatar());
                    //map.get("username")
                    list.add(map);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;  // TODO - nu e bun
    }
}
