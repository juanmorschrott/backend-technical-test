## Summary

TUI DX Backend technical Test

The base project uses lombok, so you have to install it. You can use the following guide https://www.baeldung.com/lombok-ide

## Notes

### Methods programmed asynchronously forced to work synchronous

All the Booking operations have been programed asynchronously, but some of them are forced to work 
synchronously. This has been done this way because in the Technical Test requirements states:

"All booking operations must be asynchronous and must simulate a background processing after
 receiving a booking through the API, an asynchronous event must be notified or sent."
 
Some methods in the BookingController provide information clients are waiting for, 
for this reason I force them to wait until the data is ready:
```
CompletableFuture<?> completableFuture.get();
```
In order to simulate an async behavior. I've added:
```
Thread.sleep(5000) on every BookingService method.
```
I decided to leave the request thread as a traditional call, 
but I could even make the request thread run in another thread by using:
```
DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
```

### 15 minutes data expiration

Using a real cache with the expiration time stored would be a better solution. I implemented a very naive
Cache by using a @Component. The reason is that a component is a singleton instance and  will store data 
while the application is running.
Another option could have been use a @Scheduled annotation and delete the cache values after 15 minutes.

### Exception handling

In a traditional synchronous API I would have used a @ControllerAdvice, but this doesn't work with @Async 

### Authentication

I would rather use Spring Security instead of a Custom Filter, but due the limitations of not adding any
external library I choose this option.

### Use of non unique id

A real life application expects to have unique identifiers, so I'm simulating this behavior and
repeated elements can coexist.
