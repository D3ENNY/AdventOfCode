using System.Text.RegularExpressions;

namespace challenge1
{
    internal partial class Program
    {
        [GeneratedRegex("\\d+")]
        private static partial Regex numberRegex();
        private List<List<int>[]> contentFile = new();
        public Program() => Challenge1();

        private void Challenge1()
        {
            ReadFile(Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "input.txt"));
            int result = 0;
            //contentFile.ForEach(x => Console.WriteLine($"{string.Join(", ", x[0])} | {string.Join(", ", x[1])}"));
            contentFile.ForEach(line =>
            {
                int cnt = -1;
                foreach (int n in line[0])
                    if (line[1].Contains(n))
                        cnt++;
                if (cnt > -1)
                    result += (int)Math.Pow(2, cnt);

                //Console.WriteLine("result: "+ result + " --- "+(int)Math.Pow(2, cnt));
            });

            Console.WriteLine(result);

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
            numberRegex().Matches(str).Cast<Match>().Select(m => int.TryParse(m.Value, out int n) ? n : 0).ToList();

        static void Main(string[] args)
        {
            _ = new Program();
        }

    }
}