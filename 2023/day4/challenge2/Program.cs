using System.Text.RegularExpressions;
namespace challenge2;

partial class Program
{
    [GeneratedRegex("\\d+")]
    private static partial Regex NumberRegex();
    private List<List<int>[]> contentFile = new();
    public Program() => Challenge2();
    private int result = 0;

    private void Challenge2()
    {
        ReadFile(Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "input.txt"));
        for (int i = 0; i < contentFile.Count; i++)
            ResolveScratchCard(i);
        System.Console.WriteLine(result);
    }


    private void ResolveScratchCard(int index)
    {
        List<int>[] line = contentFile[index];
        int cnt = -1;

        result++;

        foreach (int n in line[0])
            if (line[1].Contains(n))
                cnt++;
        if (cnt > -1)

            for (int i = 0; i < cnt; i++)
                ResolveScratchCard(index+i);
    }



    private void ReadFile(string path)
    {
        try
        {
            Array.ForEach(File.ReadAllLines(path), line =>
            {
                string[] part = line.Split('|');
                contentFile.Add(new[] { GetNumberList(part[0].Trim().Split(':')[1]), GetNumberList(part[1]) });
            });
        }
        catch (Exception)
        {
            throw;
        }
    }
    private List<int> GetNumberList(string str) =>
            NumberRegex().Matches(str).Cast<Match>().Select(m => int.TryParse(m.Value, out int n) ? n : 0).ToList();

    static void Main(string[] args)
    {
        _ = new Program();
    }
}
