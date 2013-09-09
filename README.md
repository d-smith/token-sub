Simple code to parse a property file, and to parse a karaf feature repository file, substituting property values tokenized by surrounding then with triple hash marks (###) with values pulled from a properties file.

Unfortunately, Karaf feature files mix XML content with property file content, so this implementation assumes the convention used by karaf - the config tags appear on their own line, and each property name = value pair appears on its own line.
