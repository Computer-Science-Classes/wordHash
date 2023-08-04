# Text Analyzer

This Java application fetches a text from a URL, processes the text into words, and adds them into a hash set. It then prints various statistics about the hash set, including the number of words, the number of buckets, the number of used buckets, the load factor, and the memory efficiency factor.

## Usage

To run the application, execute the `main` method in the `App` class. The application currently fetches the text from a hard-coded URL, but this can be modified to fetch from a different URL if desired.

## Dependencies

This application requires Java 11 or later.

## Error Handling

The application includes basic error checking and handling. If an error occurs during the execution of the application, an error message will be printed to the console, along with a stack trace.

## Note

The `normalize` method in the `App` class removes all special characters from a word, which may not be the desired behavior in all cases. This method could be improved to handle special characters more intelligently.
