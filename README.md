# server_client
Customer - Server and Readers - Writers
The server allows multiple simultaneous customer connections.
The server manages a simple data structure that corresponds (roughly) to a table with
airport routes.
Readers are few and far between. But each registration takes a long time.
You can create a writing client that performs a record in sparse time
intervals, but better to have 2 or 3 repeat customers at least to exist
more synchronization.
