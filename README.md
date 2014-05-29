BitX-Android
============

Work in progress.

An Android client for the BitX API. See https://bitx.co.za/api

Todo
----

* Publish to Maven Central
* Implement remaining API methods
  * **Order book** `GET /api/1/orderbook?pair=XBTZAR`
  * **Trades** `GET /api/1/trades?pair=XBTZAR`
  * **List orders** `GET /api/1/listorders?pair=XBTZAR`
  * **Post order** `POST /api/1/postorder`
  * **Stop order** `POST /api/1/stoporder`
  * **Create receive address** `POST /api/1/funding_address?asset=XBT`


Usage
----

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