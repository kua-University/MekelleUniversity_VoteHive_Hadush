import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference

class FirebaseHelper {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun getCandidatesFromDatabase(): DatabaseReference {
        return database.getReference("candidates")
    }
}
