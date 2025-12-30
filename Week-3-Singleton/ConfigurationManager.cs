using System;
using System.Runtime.CompilerServices;

// --- ConfigurationManager.cs içeriği ---
public sealed class ConfigurationManager
{
    // 'volatile' keyword'ü, bu değişkenin değerinin
    // farklı thread'ler tarafından anlık olarak doğru okunmasını sağlar.
    private static volatile ConfigurationManager _instance;
    
    // Thread-safe kilitleme mekanizması için kullanılacak yardımcı nesne.
    private static readonly object _lock = new object();

    public string DatabaseConnectionString { get; private set; }

    // Kurucu (constructor) 'private' olmalı.
    private ConfigurationManager()
    {
        // Örnek ayar yüklemesi
        DatabaseConnectionString =
            "Server=.;Database=BookVerse;Integrated Security=True;";
        
        // Logger'ı çağırarak konfigürasyonun ne zaman oluştuğunu görelim.
        Logger.Instance.Log("ConfigurationManager olusturuldu!");
    }

    // Singleton nesnesine erişim noktası (Property)
    public static ConfigurationManager Instance
    {
        get
        {
            // Double-Checked Locking (Çift Kontrollü Kilitleme)
            // 1. Kontrol (Kilitleme maliyetinden kaçınmak için)
            if (_instance == null)
            {
                // 2. Kilitleme (Sadece bir thread'in girmesi için)
                lock (_lock)
                {
                    // 3. Kontrol (Kilitlenen thread'den sonra bekleyen
                    // thread'lerin tekrar nesne oluşturmaması için)
                    if (_instance == null)
                    {
                        _instance = new ConfigurationManager();
                    }
                }
            }
            return _instance;
        }
    }
}
