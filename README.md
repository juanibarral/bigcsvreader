# bigcsvreader
Library to read big files in csv format. The main class is CSVFileScanner that reads each line of the file and porcess it with a given function. This function must extend LineCallableFunction class. It also provides some utility functions to read CSV files.

### Usage
The most simple case is to print out the file in the console:
```
try {
	new CSVFileScanner("my/data/path.csv", true, new LineCallableFunction(true){
		public Void call()
		{
			String[] splitted = line.split(";", -1);
			System.out.println(splitted[0] + "\t" + splitted[1]);
			logger.updateLabel(Utils.TO_INT.format(lineCounter));
			return null;
		}
	});
} catch (Exception e) {
	e.printStackTrace();
}
```
#### CSVFileScanner
Scans a big CSV file and let the LineCallableFunction process each line of the file. 
##### Constructor Arguments
* filepath: String with the filepath to read
* avoidFirst: tells the method to skip the first line that is usually the headers.
* callableFunction: Function that will process the line
 
#### LineCallableFunction
Class that manage the file one line at a time. When a new line is read, it will call de "call" function
##### Constructor Arguments
* withFrame: It will launch a frame that can be accessed each time a line is read. 

### Utils Usage
There are two utilitary functions in the library

#### Check file
Checks if all the rows in the file have the same amount of columns. Its output prints in the console the header and at the end the number of lines read.
##### Arguments
* filepath: String with the file path to be check
* separator: String for the separator being used in the CSV
* withFrame: Boolean to check if at runtime a frame with the number of lines being read is displayed

```
try {
		Utils.checkFile("file/path", ",", true);
} catch (Exception e) {
		e.printStackTrace();
}
```
#### Check headers
Returns an array with the headers from the CSV file.
##### Arguments
* filepath: String with the file path to be check
* separator: String for the separator being used in the CSV

```
try {
		String[] headers = Utils.checkHeaders(filepath, ",");
		for(String h : headers)
		{
			System.out.println(h);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
```


