package vcmsa.ci.practicum

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class DetailedViewActivity : AppCompatActivity() {

    // Arrays to store received data
    private lateinit var songTitles: Array<String?>
    private lateinit var artistNames: Array<String?>
    private lateinit var ratings: Array<Int?>
    private lateinit var comments: Array<String?>
    private var songCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        // Receive data from main activity
        receiveDataFromIntent()

        // Initialize buttons
        initializeButtons()
    }

    /**
     * Receive data passed from MainActivity
     */
    private fun receiveDataFromIntent() {
        try {
            songTitles = intent.getStringArrayExtra("songTitles") ?: arrayOfNulls(4)
            artistNames = intent.getStringArrayExtra("artistNames") ?: arrayOfNulls(4)
            ratings = intent.getSerializableExtra("ratings") as? Array<Int?> ?: arrayOfNulls(4)
            comments = intent.getStringArrayExtra("comments") ?: arrayOfNulls(4)
            songCount = intent.getIntExtra("songCount", 0)
        } catch (e: Exception) {
            showErrorMessage("Error loading playlist data: ${e.message}")
            songCount = 0
        }
    }

    /**
     * Initialize button click listeners
     */
    private fun initializeButtons() {
        val displaySongsBtn = findViewById<Button>(R.id.btnDisplaySongs)
        val calculateAverageBtn = findViewById<Button>(R.id.btnCalculateAverage)
        val backToMainBtn = findViewById<Button>(R.id.btnBackToMain)

        displaySongsBtn.setOnClickListener {
            displaySongList()
        }

        calculateAverageBtn.setOnClickListener {
            calculateAndDisplayAverage()
        }

        backToMainBtn.setOnClickListener {
            finish() // Return to main screen
        }
    }

    /**
     * Display list of songs using loops
     */
    private fun displaySongList() {
        if (songCount == 0) {
            showErrorMessage("No songs in playlist to display!")
            return
        }

        val displayArea = findViewById<TextView>(R.id.tvDisplayArea)
        val stringBuilder = StringBuilder()

        stringBuilder.append("🎵 PLAYLIST SONGS 🎵\n")
        stringBuilder.append("=" .repeat(30) + "\n\n")

        // Use loop to iterate through songs
        for (i in 0 until songCount) {
            try {
                stringBuilder.append("Song ${i + 1}:\n")
                stringBuilder.append("Title: ${songTitles[i] ?: "Unknown"}\n")
                stringBuilder.append("Artist: ${artistNames[i] ?: "Unknown"}\n")
                stringBuilder.append("Rating: ${ratings[i] ?: 0}/5 ⭐\n")
                stringBuilder.append("Comments: ${comments[i] ?: "No comments"}\n")
                stringBuilder.append("-".repeat(25) + "\n\n")
            } catch (e: Exception) {
                stringBuilder.append("Error displaying song ${i + 1}: ${e.message}\n\n")
            }
        }

        displayArea.text = stringBuilder.toString()
    }

    /**
     * Calculate and display average rating using loops
     */
    private fun calculateAndDisplayAverage() {
        if (songCount == 0) {
            showErrorMessage("No songs in playlist to calculate average!")
            return
        }

        try {
            var totalRating = 0
            var validRatings = 0

            // Use loop to calculate average
            for (i in 0 until songCount) {
                val rating = ratings[i]
                if (rating != null && rating > 0) {
                    totalRating += rating
                    validRatings++
                }
            }

            if (validRatings == 0) {
                showErrorMessage("No valid ratings found to calculate average!")
                return
            }

            val averageRating = totalRating.toDouble() / validRatings

            val displayArea = findViewById<TextView>(R.id.tvDisplayArea)
            val result = StringBuilder()

            result.append("📊 AVERAGE RATING CALCULATION \n")
            result.append("=" .repeat(35) + "\n\n")
            result.append("Total Songs: $songCount\n")
            result.append("Valid Ratings: $validRatings\n")
            result.append("Total Rating Points: $totalRating\n")
            result.append("Average Rating: ${String.format("%.2f", averageRating)}/5.00 \n\n")

            // Display rating breakdown
            result.append("Rating Breakdown:\n")
            result.append("-".repeat(20) + "\n")

            for (i in 0 until songCount) {
                val rating = ratings[i] ?: 0
                result.append("${songTitles[i] ?: "Unknown"}: $rating/5\n")
            }

            displayArea.text = result.toString()

            // Show toast with average
            showSuccessMessage("Average Rating: ${String.format("%.2f", averageRating)}/5")

        } catch (e: Exception) {
            showErrorMessage("Error calculating average: ${e.message}")
        }
    }

    /**
     * Show error message to user
     */
    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Show success message to user
     */
    private fun showSuccessMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}