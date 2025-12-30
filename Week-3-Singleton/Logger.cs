using System;

// --- Logger.cs içeriği ---
public sealed class Logger
{
    // Lazy<T> kullanarak thread-safe ve lazy initialization sağlıyoruz.
    private static readonly Lazy<Logger> _lazy =
        new Lazy<Logger>(() => new Logger(), isThreadSafe: true);

    public static Logger Instance => _lazy.Value;

    // Kurucu private, dışarıdan 'new' ile oluşturulamaz.
    private Logger() 
    {
        // Logger'ın sadece bir kez oluştuğunu görmek için bir log ekleyebiliriz.
        Log("Logger nesnesi oluşturuldu!");
    }

    public void Log(string message)
    {
        Console.WriteLine($"{DateTime.Now:HH:mm:ss.fff} | {message}");
    }
}

