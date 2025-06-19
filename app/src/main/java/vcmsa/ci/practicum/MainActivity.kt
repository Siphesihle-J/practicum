package vcmsa.ci.practicum

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Parallel arrays to store song data (max 4 songs as per requirement)
    private val songTitles = arrayOfNulls<String>(4)
    private val artistNames = arrayOfNulls<String>(4)
    private val ratings = arrayOfNulls<Int>(4)
    private val comments = arrayOfNulls<String>(4)

    // Current number of songs in playlist
    private var currentSongCount = 0

    // Maximum number of songs allowed
    private val maxSongs = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        initializeButtons()

        // Initialize with sample data for demonstration
        initializeSampleData()
    }

    /**
     * Initialize button click listeners
     */
    private fun initializeButtons() {
        val addToPlaylistBtn = findViewById<Button>(R.id.btnAddToPlaylist)
        val viewDetailsBtn = findViewById<Button>(R.id.btnViewDetails)
        val exitBtn = findViewById<Button>(R.id.btnExit)

        addToPlaylistBtn.setOnClickListener {
            showAddSongDialog()
        }

        viewDetailsBtn.setOnClickListener {
            navigateToDetailedView()
        }

        exitBtn.setOnClickListener {
            exitApplication()
        }
    }

    /**
     * Initialize sample data for demonstration purposes
     */
    private fun initializeSampleData() {
        // Sample data as per requirements
        songTitles[0] = "Nokia"
        artistNames[0] = "Drake"
        ratings[0] = 4
        comments[0] = "Rap"

        songTitles[1] = "Work"
        artistNames[1] = "Rihana"
        ratings[1] = 1
        comments[1] = "Dance song"

        songTitles[2] = "Snooze"
        artistNames[2] = "SZA"
        ratings[2] = 2
        comments[2] = "Best love song"

        songTitles[3] = "Can We Be Lovers"
        artistNames[3] = "Teddy Pendagrass"
        ratings[3] = 3
        comments[3] = "Memories"

        currentSongCount = 4
    }

    /**
     * Show dialog to add new song to playlist
     */
    @SuppressLint("SetTextI18n")
    private fun showAddSongDialog() {
        // Check if playlist is full
        if (currentSongCount >= maxSongs) {
            showErrorMessage("Playlist is full! Maximum $maxSongs songs allowed.")
            return
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Song to Playlist")

        // Create layout for input fields
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 40, 50, 10)

        // Song title input
        val songTitleLabel = TextView(this)
        songTitleLabel.text = "Song Title:"
        layout.addView(songTitleLabel)

        val songTitleInput = EditText(this)
        songTitleInput.hint = "Enter song title"
        layout.addView(songTitleInput)

        // Artist name input
        val artistLabel = TextView(this)
        artistLabel.text = "Artist Name:"
        layout.addView(artistLabel)

        val artistInput = EditText(this)
        artistInput.hint = "Enter artist name"
        layout.addView(artistInput)

        // Rating input
        val ratingLabel = TextView(this)
        ratingLabel.text = "Rating (1-5):"
        layout.addView(ratingLabel)

        val ratingInput = EditText(this)
        ratingInput.hint = "Enter rating (1-5)"
        ratingInput.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        layout.addView(ratingInput)

        // Comments input
        val commentsLabel = TextView(this)
        commentsLabel.text = "Comments:"
        layout.addView(commentsLabel)

        val commentsInput = EditText(this)
        commentsInput.hint = "Enter your comments"
        layout.addView(commentsInput)

        builder.setView(layout)

        // Set positive button
        builder.setPositiveButton("Add Song") { _, _ ->
            addSongToPlaylist(
                songTitleInput.text.toString(),
                artistInput.text.toString(),
                ratingInput.text.toString(),
                commentsInput.text.toString()
            )
        }

        // Set negative button
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    /**
     * Add song to playlist with validation
     */
    private fun addSongToPlaylist(title: String, artist: String, ratingStr: String, comment: String) {
        try {
            // Validate inputs
            if (title.trim().isEmpty()) {
                showErrorMessage("Song title cannot be empty!")
                return
            }

            if (artist.trim().isEmpty()) {
                showErrorMessage("Artist name cannot be empty!")
                return
            }

            if (ratingStr.trim().isEmpty()) {
                showErrorMessage("Rating cannot be empty!")
                return
            }

            val rating = ratingStr.toIntOrNull()
            if (rating == null || rating < 1 || rating > 5) {
                showErrorMessage("Rating must be a number between 1 and 5!")
                return
            }

            // Add song to arrays
            songTitles[currentSongCount] = title.trim()
            artistNames[currentSongCount] = artist.trim()
            ratings[currentSongCount] = rating
            comments[currentSongCount] = comment.trim()

            currentSongCount++

            showSuccessMessage("Song added successfully!")

        } catch (e: Exception) {
            showErrorMessage("Error adding song: ${e.message}")
        }
    }

    /**
     * Navigate to detailed view screen
     */
    private fun navigateToDetailedView() {
        if (currentSongCount == 0) {
            showErrorMessage("No songs in playlist to display!")
            return
        }

        val intent = Intent(this, DetailedViewActivity::class.java)

        // Pass data to detailed view
        intent.putExtra("songTitles", songTitles)
        intent.putExtra("artistNames", artistNames)
        intent.putExtra("ratings", ratings)
        intent.putExtra("comments", comments)
        intent.putExtra("songCount", currentSongCount)

        startActivity(intent)
    }

    /**
     * Exit application with confirmation
     */
    private fun exitApplication() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit Application")
        builder.setMessage("Are you sure you want to exit?")

        builder.setPositiveButton("Yes") { _, _ ->
            finish()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
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