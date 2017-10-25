import mechanize
from bs4 import BeautifulSoup
import csv
import sys

""" Retorna uma lista de links para as conferencias e uma lista de links para conferencias que comecam com certa letra."""
def get_links(html):
	soup = BeautifulSoup(br.response().read(), "html5lib")

	tables = soup.find_all('table')
	tables = tables[2].find_all('table')
	links_conf_per_letter = tables[0].find_all('a')
	links = tables[1].find_all('a')

	return links, links_conf_per_letter

""" Recebe o nome de um arquivo e retorna uma lista com os editores que estao no arquivo."""
def get_publishers(file):
	f = open(file, 'r')
	reader = csv.reader(f)
	tmp_list = reader.next()
	f.close()
	return tmp_list

def write_file(file, conference, publishers):
	writer = csv.writer(file, delimiter=";")

	row = []
	row.append(conference)
	for p in publishers:
		row.append(p)
	#row.append(publishers)
	writer.writerow(row)

""" Verifica se possui o numero correto de argumentos."""
if len(sys.argv) != 2:
	print "usage: python", sys.argv[0], "publishers_source"
	exit()
	

br = mechanize.Browser()
br.set_handle_robots(False)
base_url = "http://wikicfp.com"
br.open("http://wikicfp.com/cfp/series?t=c&i=A")

links, links_conf_per_letter = get_links(br.response().read())

count = 0
publishers = get_publishers(sys.argv[1])

file_out = open("conferences_publisers.csv", "wb")

for link_letter in links_conf_per_letter:
	links = get_links(br.response().read())[0]
	for link in links:
		link = link['href'].replace(" ", "%20")
		br.follow_link(url=link)

		#Testando 
		soup = BeautifulSoup(br.response().read(), "html5lib")
		main_table = soup.find_all('table')[2]
		title = main_table.find_all('h2')[0].contents[0]

		if len(main_table.find_all('div')) == 0:
			content = '\t'.join(unicode(e) for e in main_table.contents)
		else:
			main_content = main_table.find_all('div')[0]
			content = '\t'.join(unicode(e) for e in main_content.contents)

		#content_list = content.split()
		
		list_publishers = []
		for publisher in publishers:
			if publisher in content:
				list_publishers.append(publisher)

		if " published " in content:
			count += 1
		
		write_file(file_out, title, list_publishers)
		#Testando

		print title
		br.back()
	
	br.follow_link(url=link_letter['href'])

print count