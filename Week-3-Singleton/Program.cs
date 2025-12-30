// See https://aka.ms/new-console-template for more information
using System;
using System.Collections.Concurrent;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Threading.Tasks;

public class Program
{
    public static void Main()
    {
        Logger.Instance.Log("=== Singleton Deneyi Basladi ===");
        
        // 1. Sıralı Erişim Testi
        new Alpha().Run();
        new Beta().Run();
        new Gamma().Run();
        new Delta().Run();
        new Epsilon().Run();

        Logger.Instance.Log("=== Thread-Safe Testi ===");

        // 2. Paralel Erişim Testi
        var ids = new ConcurrentBag<int>(); // Thread-safe koleksiyon
        Parallel.For(0, 10, i =>
        {
            var cfg = ConfigurationManager.Instance;
            int hashCode = RuntimeHelpers.GetHashCode(cfg);
            ids.Add(hashCode);
            Logger.Instance.Log($"[Parallel {i}] cfgID={hashCode}");
        });

        Logger.Instance.Log(
            $"Benzersiz cfgID sayisi: {ids.Distinct().Count()} (beklenen: 1)");

        Logger.Instance.Log("=== Deney Tamamlandi ===");
    }

    // Yardımcı metot
    public static void Show(string name)
    {
        var cfg = ConfigurationManager.Instance;
        Logger.Instance.Log(
            $"[{name}] cfgID={RuntimeHelpers.GetHashCode(cfg)} | conn={cfg.DatabaseConnectionString}");
    }
}
