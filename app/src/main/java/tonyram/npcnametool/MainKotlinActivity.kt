package tonyram.npcnametool

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.Random

/**
 * @author asadana
 * @since 6/25/18
 */
class MainKotlinActivity : AppCompatActivity() {

	lateinit var firstNameStream: InputStreamReader
	lateinit var lastNameStream: InputStreamReader
	lateinit var brFirstName: BufferedReader
	lateinit var brLastName: BufferedReader
	var numLinesFirstName: Int = 0
	var numLinesLastName: Int = 0
	lateinit var randomFirstName: Random
	lateinit var randomLastName: Random
	var desiredLineFirstName: Int = 0
	var desiredLineLastName: Int = 0
	lateinit var newFirstName: String
	lateinit var newLastName: String
	var lineCounterFirstName: Int = 0
	var lineCounterLastName: Int = 0
	lateinit var fullName: StringBuilder
	lateinit var mDrawerLayout: DrawerLayout
	lateinit var mToggle: ActionBarDrawerToggle

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.navigation_bar)
		init()
	}

	private fun init() {
		mDrawerLayout = findViewById(R.id.drawerLayoutMain) as DrawerLayout
		val newNameText = findViewById(R.id.nameValue) as TextView
		val randomizeButton = findViewById(R.id.randomizeButton) as Button
		randomizeButton.setOnClickListener {
			firstNameStream = InputStreamReader(resources.openRawResource(R.raw.firstnames))
			lastNameStream = InputStreamReader(resources.openRawResource(R.raw.lastnames))
			brFirstName = BufferedReader(firstNameStream)
			brLastName = BufferedReader(lastNameStream)
			numLinesFirstName = 100
			numLinesLastName = 70
			randomFirstName = Random()
			randomLastName = Random()
			desiredLineFirstName = randomFirstName.nextInt(numLinesFirstName)
			desiredLineLastName = randomLastName.nextInt(numLinesLastName)
			newFirstName = ""
			newLastName = ""
			lineCounterFirstName = 0
			lineCounterLastName = 0
			fullName = StringBuilder()
			try {
				do {
					newFirstName = brFirstName.readLine()
					if (lineCounterFirstName == desiredLineFirstName) {
						break
					}
					lineCounterFirstName++
				} while (newFirstName != null)

				newLastName = brLastName.readLine()
				while (newLastName != null) {
					if (lineCounterLastName == desiredLineLastName) {
						break
					}
					lineCounterLastName++
					newLastName = brLastName.readLine()
				}
			}
			catch (e: IOException) {
				e.printStackTrace()
			}
			fullName.append(newFirstName)
			fullName.append(" ")
			fullName.append(newLastName)
			newNameText.text = fullName.toString()
		}

//		//Add Saved name to List
//		val savedList = findViewById(R.id.listSaved) as ListView
//		val saveButton = findViewById(R.id.saveButton) as Button
//		val descriptionText = findViewById(R.id.descriptionInput) as EditText
//		val locationText = findViewById(R.id.locationInput) as EditText
//		val listItems = ArrayList<String>()
//		val stringAdapter = ArrayAdapter(this, R.layout.saved_list, listItems)
//		//savedList.setAdapter(stringAdapter);
//
//		saveButton.setOnClickListener {
//			listItems.add(fullName.toString())
//			listItems.add(descriptionText.text.toString())
//			listItems.add(locationText.text.toString())
//			stringAdapter.notifyDataSetChanged()
//		}
//		savedList.setOnItemClickListener(AdapterView.OnItemClickListener { parent, v, position, id ->
//			//do something
//			Toast.makeText(this@MainKotlinActivity, "Clicked", Toast.LENGTH_LONG).show()
//		})
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return if (mToggle.onOptionsItemSelected(item)) {
			true
		}
		else super.onOptionsItemSelected(item)


	}
}