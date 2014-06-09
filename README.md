BitX-Android
============

An unofficial Android client for the BitX API. See https://bitx.co.za/api

Work in progress.

Todo
----

* Publish to Maven Central
* Implement remaining API methods
  * **List withdrawal requests** `GET /api/1/withdrawals`
  * **Request a withdrawal** `POST /api/1/withdrawals`
  * **Particular withdrawal request** `POST /api/1/withdrawals/{id}`
  * **Cancel withdrawal request** `DELETE /api/1/withdrawals/{id}`


Usage
----

Compile `.aar` and include in your Android project. This will be simplified once the library has been published on Maven Central.
Refer to BitXAndroidSampleApp.

```java
BitXClient client = new BitXClient("id", "secret");

//example call to get ticker information
client.ticker(new Callback<Ticker>() {
    @Override
    public void success(Ticker ticker, Response response) {
        Log.d("BitXClient", String.format("TICKER: bid: %s, ask: %s, lastTrade: %s",
                ticker.bid, ticker.ask, ticker.lastTrade));
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e("BitXClient", "Error: Failed to get ticker information.", error);
    }
});

```